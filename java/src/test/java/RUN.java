import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import liu.com.JOSN;
import liu.com.excell;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RUN {


    static Scanner sc = new Scanner(System.in);
    static String codeforces_URL="https://codeforces.com/api/user.info?checkHistoricHandles=false&handles=";
    static String nowcode_URL="https://ac.nowcoder.com/acm/user/=";
    static String atcode_URL="https://atcoder.jp/users/=";
    static int LHC_2=2;
    static Fuck [][]fuck=new Fuck[LHC_2][100];
    static codeforces_color [] cf_color=new codeforces_color[10];
    static nowcode_color [] nc_color=new nowcode_color[7];
    public static void main(String[] args) throws Exception {
        for(int i=0;i<10;i++)
        {
            cf_color[i]=new codeforces_color();
        }
        //1
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
        cf_color[0].color=" <font color=#0000>";
        cf_color[1].color=" <font color=#808080>";
        cf_color[2].color=" <font color=#008000>";
        cf_color[3].color=" <font color=#03a89e>";
        cf_color[4].color=" <font color=#0000ff>";
        cf_color[5].color=" <font color=aa00aa>";
        cf_color[6].color=" <font color=#ff8c00>";
        cf_color[7].color=" <font color=#ff0000>";
        cf_color[8].color=" <font color=#ff0000>";
        cf_color[9].color=" <font color=#000000>";
        String end="</font>";
        //
        for(int i=0;i<7;i++)
        {
            nc_color[i]=new nowcode_color();
        }
        //
        nc_color[0].rank="rate-score1";
        nc_color[1].rank="rate-score2";
        nc_color[2].rank="rate-score3";
        nc_color[3].rank="rate-score4";
        nc_color[4].rank="rate-score5";
        nc_color[5].rank="rate-score6";
        nc_color[6].rank="rate-score7";
        nc_color[0].color="<font color=#b4b4b4>";
        nc_color[1].color="<font color=#c177e7>";
        nc_color[2].color="<font color=#5ea1f4>";
        nc_color[3].color="<font color=#25bb9b>";
        nc_color[4].color="<font color=#ffd700>";
        nc_color[5].color="<font color=#ffa500>";
        nc_color[6].color="<font color=red>";
        //fuck
        excell x = new excell();
        int LHC=x.LHC;
        JOSN api = new JOSN();
        x.EXC();
        for(int i=0;i<LHC;i++)
        {
            for(int j=0;j<x.Length[i];j++)
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
                            System.out.println(o2);
                            fuck[i][j].CodeForces_rank=o2;
                            if(o2==null)
                            {
                                fuck[i][j].CodeForces_rank = "unrated";
                                fuck[i][j].CodeForces_color = cf_color[0].color;
                            }
                            else
                            {
                                for (int e = 0; e < 10; e++) {
                                    if (cf_color[e].rank.equals(o2)) {
                                        fuck[i][j].CodeForces_color = cf_color[e].color;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    else if(k==2)//nowcode
                    {
                        fuck[i][j].Nowcode=x.Man[i][j].nowcode;
                        String userId = fuck[i][j].Nowcode; // 替换为实际用户ID
                        String url = "https://ac.nowcoder.com/acm/contest/rating-index?searchUserName=" + userId;
                        Document doc = Jsoup.connect(url).get();

                        // 根据网页结构选择rating分所在的元素
                        Element ratingElement = doc.selectFirst("body > div > div.nk-main.clearfix.js-container > div > div > div.platform-mod-bd.js-list > table > tbody > tr > td:nth-child(5) > span"); // 替换为实际CSS选择器

                        if (ratingElement != null) {
                            String rating = ratingElement.text();
                            fuck[i][j].Nowcode_rating=rating;
                            int score = Integer.parseInt(rating);
                            if(score<=699)
                            {
                                fuck[i][j].Nowcode_color=nc_color[0].color;
                            }
                            else if(score>=700&&score<=1099)
                            {
                                fuck[i][j].Nowcode_color=nc_color[1].color;
                            }
                            else if(score>=1100&&score<=1499)
                            {
                                fuck[i][j].Nowcode_color=nc_color[2].color;
                            }
                            else if(score>=1500&&score<=1999)
                            {
                                fuck[i][j].Nowcode_color=nc_color[3].color;
                            }
                            else if(score>=2000&&score<=2399)
                            {
                                fuck[i][j].Nowcode_color=nc_color[4].color;
                            }
                            else if(score>=2400&&score<=2799)
                            {
                                fuck[i][j].Nowcode_color=nc_color[5].color;
                            }
                            else if(score>=2800)
                            {
                                fuck[i][j].Nowcode_color=nc_color[6].color;
                            }
                        }
                        else
                        {
                            fuck[i][j].Nowcode_rating="0";
                            int score=0;
                            if(score<=699)
                            {
                                fuck[i][j].Nowcode_color=nc_color[0].color;
                            }
                            else if(score>=700&&score<=1099)
                            {
                                fuck[i][j].Nowcode_color=nc_color[1].color;
                            }
                            else if(score>=1100&&score<=1499)
                            {
                                fuck[i][j].Nowcode_color=nc_color[2].color;
                            }
                            else if(score>=1500&&score<=1999)
                            {
                                fuck[i][j].Nowcode_color=nc_color[3].color;
                            }
                            else if(score>=2000&&score<=2399)
                            {
                                fuck[i][j].Nowcode_color=nc_color[4].color;
                            }
                            else if(score>=2500&&score<=2799)
                            {
                                fuck[i][j].Nowcode_color=nc_color[5].color;
                            }
                            else if(score>=2800)
                            {
                                fuck[i][j].Nowcode_color=nc_color[6].color;
                            }
                        }

                    }

//                    else if(k==3)//atcode
//                    {
//                        String atcode_json=api.run(atcode_URL+x.Man[i][j].atcode);
//                    }
                }
            }
        }

        List<String>arr = new ArrayList<String>();
        String one="| 年级 | 姓名 | Codeforces | Nowcoder | AtCoder |\n";
        String tow="| :---: | :---: | :---: | :---: | :---: |\n";
        for(int i=0;i<LHC;i++)
        {
            for(int j=0;j<x.Length[i];j++)
            {
                String two="| "+" | "+fuck[i][j].Name+" | "+"["+fuck[i][j].CodeForces_color+fuck[i][j].CodeForces+" "+"("+fuck[i][j].CodeForces_rating+")"+end+"]"+"(https://codeforces.com/profile/"+fuck[i][j].CodeForces+")"+" | "+"["+fuck[i][j].Nowcode_color+fuck[i][j].Nowcode+"("+fuck[i][j].Nowcode_rating+")"+end+"]"+"(https://ac.nowcoder.com/acm/contest/rating-index?searchUserName="+fuck[i][j].Nowcode+")"+" | "+fuck[i][j].Atcode+" | "+fuck[i][j].Atcode_rating+" | "+"\n";
                arr.add(two);
            }
        }
        String ans="";
        ans+=one;
        ans+=tow;
        int yyye=2023;
        int opt=0;
        for(int i=0;i<LHC;i++)
        {
            String tree="| "+yyye+" |  |  |  |  |\n";
            ans+=tree;
            for(int j=opt;j<x.Length[i];j++)
            {
                ans+=arr.get(j);
            }
            opt+=x.Length[i];
            if(i+1<LHC)
            {
                x.Length[i+1]+=opt;
            }
            yyye++;
        }

        Gson gson = new Gson();
        String json = gson.toJson(fuck);
        File file = new File("src/main/resources/ingrat.md");
        if(file.exists())
        {
            System.out.println("文件已经存在");
            FileWriter fileWritter = new FileWriter ("src/main/resources/ingrat.md");
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(ans);
            bufferWritter.close();
        }
        else
        {
            file.createNewFile();
            System.out.println("文件不存在，已创建");
        }
        System.out.println(fuck[0][0].Nowcode);
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