package actions.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;

/**
 * Created by Administrator on 14-4-7.
 */
public class UploadExcelAction {

    private static Logger LOG = LoggerFactory.getLogger(UploadExcelAction.class);

    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream("C:\\Users\\Administrator\\Desktop\\test.xls");
        Workbook wb = new HSSFWorkbook(fin);
        Sheet sheet = wb.getSheetAt(0);
        // Decide which rows to process
        int rowStart = Math.min(10, sheet.getFirstRowNum());
        int rowEnd = Math.min(5000, sheet.getLastRowNum());
        LOG.info("rowStart=" + rowStart + ",rowEnd=" + rowEnd);
        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = sheet.getRow(rowNum);
            int lastColumn = row.getLastCellNum();
            for (int cn = 0; cn < lastColumn; cn++) {
                Cell cell = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    LOG.info("空白单元格" + row.getRowNum());
                } else {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            LOG.info(cell.getRichStringCellValue().getString());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                LOG.info(cell.getDateCellValue().toString());
                            } else {
                                LOG.info(String.valueOf((long) (cell.getNumericCellValue())));
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            LOG.info(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            LOG.info(cell.getCellFormula());
                            break;
                        default:
                            LOG.info("");
                    }
                }
                LOG.info(" ");
            }
            LOG.info("");
        }
    }
}
