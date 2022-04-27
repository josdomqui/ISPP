package org.springframework.samples.commandfast.payments;

import java.time.LocalDate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.line.LineService;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.plate.Plate;
import org.springframework.samples.commandfast.plate.PlateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.util.logging.Logger;


import java.io.File;
import java.io.FileNotFoundException;

@Service
public class PaymentService {

	private final CommandService commandService;
	private final LineService lineService;
	private final PlateService plateService;
	@Autowired
	PaymentRepository paymentRepository;

	public PaymentService(CommandService commandService, PaymentRepository paymentRepository, LineService lineService, PlateService plateService) {
		super();
		this.commandService = commandService;
		this.lineService = lineService;
		this.plateService = plateService;
		this.paymentRepository = paymentRepository;
	}

	@Transactional
	public void savePayment(Payment payment) throws DataAccessException {
		paymentRepository.save(payment);
	}

	@Transactional
	public Payment makePayment(Double amount, Mesa table) {
		Payment payment = new Payment();

		payment.setAmount(amount);

		payment.setTable(table);

		payment.setDate(LocalDate.now());

		savePayment(payment);

		return payment;

	}

	public Optional<Payment> getPaymentById(Integer id) {

		return paymentRepository.findById(id);
	}

	public List<Payment> getAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}

	public String generateRecipt(int idComanda) {
		String fileName = "recipt.pdf";
		try {
			File file = new File(fileName);
			PdfWriter pdfWriter = new PdfWriter(file);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument);
			
			// Add logo banner to recipt
			String imageFile = "src/main/resources/static/resources/images/pdf/banner-recibo.jpeg";
			ImageData data = ImageDataFactory.create(imageFile);
			Image img = new Image(data);
			document.add(img);

			Paragraph paragraph = new Paragraph();
			paragraph.setTextAlignment(TextAlignment.CENTER);
			Text blankLine = new Text("\n");
			paragraph.add(blankLine);
			
			// add font for pretext
			Style italic = new Style();
			PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
			italic.setFont(font).setFontSize(12);
			// add font for body
			Style body = new Style();
			PdfFont fontRoman = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
			body.setFont(fontRoman).setFontSize(14);
			
			String bodyMessage = "";
			if (idComanda == 0) { // subscription
				bodyMessage = "Gracias por suscribirte a CommandFast. Su id de compra es: 98892";
				paragraph.add(new Text(bodyMessage).addStyle(body));
			} else {
				Double price = 0.0;
				Optional<Command> command = commandService.findIdCommands(idComanda);
				Collection<Line> lineas = this.lineService.findLineByCommandId(idComanda);
				Collection<Plate> platos = this.plateService.findAllPlates();
				
				// pretext
				String pretext = "Gracias por visitar " + command.get().getRestaurante().getName() + ". Esperamos que vuelva pronto.";
				paragraph.add(new Text(pretext).addStyle(italic));
				paragraph.add(blankLine);
				pretext = "Número de mesa: " + command.get().getMesa().getNumber().toString() + ". Número de comensales: " + command.get().getCostumers();
				paragraph.add(new Text(pretext).addStyle(italic));
				paragraph.add(blankLine);
				paragraph.add(blankLine);
				paragraph.add(blankLine);
				
				// info plate by plate
				for (Line linea: lineas) {
					for (Plate plato: platos) {
						if(linea.getPlate().getId().equals(plato.getId()) && linea.getQuantity() != 0) {
							Double total = plato.getCost()*linea.getQuantity();
							bodyMessage = plato.getName() + " = " + plato.getCost().toString() + "€ x" + linea.getQuantity().toString() + " = " + String.format("%.2f", total) + "€"; 
							paragraph.add(new Text(bodyMessage).addStyle(body));
							paragraph.add(blankLine);
						}
					}
				}
				price = command.get().getPrice();
				bodyMessage = "=========================================";
				paragraph.add(new Text(bodyMessage).addStyle(body));
				paragraph.add(blankLine);
				bodyMessage = "Coste total del pedido: " + price + "€";
				paragraph.add(new Text(bodyMessage).addStyle(body));
				paragraph.add(blankLine);
				bodyMessage = "Id de compra: 932442";
				paragraph.add(new Text(bodyMessage).addStyle(italic));
			}
			
			document.add(paragraph);
			document.close();
			pdfWriter.close();
			System.out.println("PDF creado");
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			
		}
		return fileName;
	}
}
