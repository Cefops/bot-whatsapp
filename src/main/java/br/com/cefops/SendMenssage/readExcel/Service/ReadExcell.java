package br.com.cefops.SendMenssage.readExcel.Service;

import br.com.cefops.SendMenssage.meta.whatsapp.model.WpMessageModel;
import br.com.cefops.SendMenssage.meta.whatsapp.services.SendRequestService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReadExcell {
    @Autowired
    private SendRequestService service;

    public List<WpMessageModel> readExcll(String path,String id,String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(path);



        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            var o = i == 0 ? null : data.put(i, new ArrayList<String>());

            for (Cell cell : row) {
                if (i != 0) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(i).add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                data.get(i).add(String.valueOf(dateFormat.format(DateUtil.getJavaDate(cell.getNumericCellValue()))));
                            } else {
                                Double len = cell.getNumericCellValue();
                                String tele = len.toString();
                                if (tele.length() == 15) {
                                    var str = new DecimalFormat("##").format(len);

                                    data.get(i).add(str);


                                } else {
                                    String val=String.valueOf(cell.getNumericCellValue());
                                    String price= new DecimalFormat("#.00#").format(cell.getNumericCellValue());
                                    data.get(i).add(price);

                                }


                            }
                            break;
                    }
                }

            }

            i++;
        }
        workbook.close();

        List<WpMessageModel>
                messageList = data.values().stream().map(m -> {

            WpMessageModel message = new WpMessageModel();
            message.setPhone(m.get(0));
            message.setName(m.get(1));
            message.setDate(m.get(2));
            message.setValue(m.get(3));
            return message;
        }).collect(Collectors.toList());
        service.sendNewMenssage(messageList,id,fileName);
        return messageList;

    }
}
