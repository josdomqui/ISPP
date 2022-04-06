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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
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
			String mensajePDF = "";
			if (price == 0.0) {
				mensajePDF = "Gracias por suscribirte a CommandFast. Su id de compra es: 98892";
			} else {
				mensajePDF = "Su pedido ha costado " + price + " euros.";
			}
			Paragraph paragraph = new Paragraph(mensajePDF);
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
