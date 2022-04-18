package org.springframework.samples.commandfast.payments;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

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

	public String generateRecipt(Double price) {
		String fileName = "recipt.pdf";
		try {
			File file = new File(fileName);
			PdfWriter pdfWriter = new PdfWriter(file);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument);
			// Add logo banner to recipt
			String imageFile = "./src/main/resources/static/resources/images/pdf/banner-recibo.png";
			ImageData data = ImageDataFactory.create(imageFile);
			Image img = new Image(data);
			document.add(img);

			Paragraph paragraph = new Paragraph();
			Text blankLine = new Text("\n");
			paragraph.add(blankLine);
			paragraph.add(blankLine);
			String mensajePDF = "";
			if (price == 0.0) {
				mensajePDF = "Gracias por suscribirte a CommandFast. Su id de compra es: 98892";
			} else {
				mensajePDF = "Su pedido ha costado " + price + " euros.";
			}
			paragraph.add(new Text(mensajePDF));
			document.add(paragraph);
			document.close();
			pdfWriter.close();
			System.out.println("PDF creado");
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return fileName;
	}
}
