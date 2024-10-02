
import java.io.*;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import liu.com.JOSN;
import liu.com.excell;

public class RUN {

    static Scanner sc = new Scanner(System.in);
    static String codeforces_URL="https://codeforces.com/api/user.info?checkHistoricHandles=false&handles=";
    static String nowcode_URL="https://ac.nowcoder.com/acm/user/=";
    static String atcode_URL="https://atcoder.jp/users/=";
    static Fuck [][]fuck=new Fuck[2][3];
    static codeforces_color [] cf_color=new codeforces_color[10];
    static nowcode_color [] nc_color=new nowcode_color[5];
    public static void main(String[] args) throws Exception {
        for(int i=0;i<10;i++)
        {
            cf_color[i]=new codeforces_color();
        }
        //
        cf_color[0].rank="unrated";
        cf_color[1].rank="newbie";
        cf_color[2].rank="pupil";
        cf_color[3].rank="specialist";
        cf_color[4].rank="expert";
        cf_color[5].rank="candidate master";
        cf_color[6].rank="master";
        cf_color[7].rank="international master";
        cf_color[8].rank="grandmaster";
        cf_color[9].rank="headquarters";
        //
        for(int i=0;i<5;i++)
        {
            nc_color[i]=new nowcode_color();
        }
        //
        nc_color[0].rank="rate-score1";
        nc_color[1].rank="rate-score2";
        nc_color[2].rank="rate-score3";
        nc_color[3].rank="rate-score4";
        nc_color[4].rank="rate-score5";
        //fuck
        excell x = new excell();
        JOSN api = new JOSN();
        x.EXC();
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<x.length;j++)
            {
                fuck[i][j]=new Fuck();
                fuck[i][j].Name=x.Man[i][j].name;
                fuck[i][j].CodeForces=x.Man[i][j].codeforces;
                for(int k=1;k<x.weight;k++)
                {
                    if (k==1)//codeforces
                    {
                        String codeforces_json=api.run(codeforces_URL+x.Man[i][j].codeforces);
                        JSONObject jsonObject = JSONObject.parseObject(codeforces_json);
                        if(jsonObject.get("status").equals("OK"))
                        {
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            JSONObject jsonObject1 = new JSONObject();
                            for(int l=0;l<jsonArray.size();l++)
                            {
                                jsonObject1 = jsonArray.getJSONObject(l);
                            }
                            String o1=jsonObject1.getString("rating");
                            fuck[i][j].CodeForces_rating=o1;
                            String o2=jsonObject1.getString("rank");
                            fuck[i][j].CodeForces_rank=o2;
                            for(int e=0;e<10;e++)
                            {
                                if(cf_color[e].rank.equals(o2))
                                {
                                    fuck[i][j].CodeForces_color=cf_color[e].color;
                                    break;
                                }
                            }
                        }
                    }
//                    else if(k==2)//nowcode
//                    {
//                        String nowcode_json=api.run(nowcode_URL+x.Man[i][j].nowcode);
//                        JSONObject jsonObject = JSONObject.parseObject(nowcode_json);
//
//                    }
//                    else if(k==3)//atcode
//                    {
//                        String atcode_json=api.run(atcode_URL+x.Man[i][j].atcode);
//                    }
                }
            }
        }
//        for(int i=0;i<2;i++)
//        {
//            for(int j=0;j<x.length;j++)
//            {
//                System.out.println(fuck[i][j].Name+" "+fuck[i][j].CodeForces+" "+fuck[i][j].CodeForces_rating+" "+fuck[i][j].CodeForces_rank);
//            }
//        }
        Gson gson = new Gson();
        String json = gson.toJson(fuck);

        File file = new File("src/main/resources/data.json");
        if(file.exists())
        {
            System.out.println("文件已经存在");
            FileWriter fileWritter = new FileWriter ("src/main/resources/data.json");
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(json);
            bufferWritter.close();
        }
        else
        {
            file.createNewFile();
            System.out.println("文件不存在，已创建");
        }

        System.out.println(json);
    }

    static class Fuck
    {
        String Name;
        String CodeForces;
        String CodeForces_rating;
        String CodeForces_color;
        String CodeForces_rank;
        String Nowcode;
        String Nowcode_rating;
        String Nowcode_color;
        String Nowcode_rank;
        String Atcode;
        String Atcode_rating;
    }
    static class codeforces_color
    {
        String rank;
        String color;
    }

    static class nowcode_color
    {
        String rank;
        String color;
    }
}
