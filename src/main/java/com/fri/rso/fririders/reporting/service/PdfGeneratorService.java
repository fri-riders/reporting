package com.fri.rso.fririders.reporting.service;

import com.fri.rso.fririders.reporting.data.PdfSerializable;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PdfGeneratorService {

    public <T extends PdfSerializable> ByteArrayInputStream generateReport(String heading, List<T> data) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            final Map<String, Function<T, String>> serializationData = data.get(0).serializationData();
            final int numColumns = serializationData.keySet().size();
            final PdfPTable table = new PdfPTable(numColumns);
            table.setWidthPercentage(90);

            final int[] widths = new int[numColumns];
            Arrays.fill(widths, 1);
            table.setWidths(widths);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            serializationData.keySet()
                    .forEach(s -> {
                        PdfPCell hcell = new PdfPCell(new Phrase(s, headFont));
                        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(hcell);
                    });

            data.forEach(obj ->
                    serializationData.forEach((key, value) -> {
                            PdfPCell cell = new PdfPCell(new Phrase(
                                    Optional.of(value.apply(obj)).orElse("")
                            ));
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                    }));

            PdfWriter.getInstance(document, out);
            document.open();
            Chunk chunk = new Chunk(heading, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC));
            document.add(new Paragraph(chunk));
            document.add(new Paragraph(" "));
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfGeneratorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
