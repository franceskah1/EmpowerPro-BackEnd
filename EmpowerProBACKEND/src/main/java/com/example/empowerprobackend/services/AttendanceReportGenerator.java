package com.example.empowerprobackend.services;
import com.example.empowerprobackend.models.Attendance;
import com.example.empowerprobackend.models.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class AttendanceReportGenerator {
    public static byte[] generatePdfReport(User user, List<Attendance> attendanceList) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document=new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph("User ID: " + user.getId()));
            document.add(new Paragraph("User Name: " + user.getFirstName()));
            document.add(new Paragraph("Attendance Records:"));
            for (Attendance attendance : attendanceList) {
                document.add(new Paragraph("Date: " + attendance.getAttendanceDate()));
                document.add(new Paragraph("Time In: " + attendance.getTimeIn()));
                document.add(new Paragraph("Time Out: " + attendance.getTimeOut()));
                document.add(new Paragraph("Status: " + attendance.getAttendanceStatus()));
                document.add(new Paragraph("Note: " + attendance.getNote()));
                document.add(new Paragraph("Hours of work:"+attendance.getHoursWorked()));
            }

            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF report", e);
        }
    }
}

