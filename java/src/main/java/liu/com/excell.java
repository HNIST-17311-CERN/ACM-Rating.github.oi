package liu.com;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class excell {
    static String PATH= "src/main/resources/rating.xlsx";
    public static int length=0;
    public static int weight=0;
    public static int LHC=1;
    //2023届数据
    public static man[][] Man=new man[LHC][100];


    public void EXC() throws Exception {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH);
        //1，创建一个工作簿
        Workbook workbook = new XSSFWorkbook(inputStream);
        //2,得到表
        for (int i = 0; i < LHC; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            length = sheet.getLastRowNum();
            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j + 1);
                Man[i][j] = new man();
                weight = row.getLastCellNum();
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    String str = cell.getStringCellValue();
                    //System.out.println(k+" "+str);
                    if (k == 0) {
                        Man[i][j].name = str;
                    } else if (k == 1) {
                        Man[i][j].codeforces = str;
                    } else if (k == 2) {
                        Man[i][j].nowcode = str;
                    } else if (k == 3) {
                        Man[i][j].atcode = str;
                    }
                    //System.out.println(Man[i][j].name+","+Man[i][j].codeforces+","+Man[i][j].nowcode+","+Man[i][j].atcode);
                }
            }
        }
//        for(int i=0;i<2;i++)
//        {
//            for(int j=0;j<3;j++)
//            {
//                System.out.println(Man[i][j].name+","+Man[i][j].codeforces+","+Man[i][j].nowcode+","+Man[i][j].atcode);
//            }
//        }
        inputStream.close();
    }

    public static class man {
        public String name;
        public String codeforces;
        public String nowcode;
        public String atcode;
    }
}
