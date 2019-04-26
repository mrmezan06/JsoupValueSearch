package com.mezan.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebsite();
            }
        });
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    //Document doc = Jsoup.connect("https://www.rapidtables.com/web/color/html-color-codes.html").get();
                    Document doc = Jsoup.connect("https://www.rapidtables.com/web/color/html-color-codes.html").get();
                    /*String title = doc.title();
                    //Elements links = doc.select("a[href]");
                    Elements links = doc.select("a[title]");

                    builder.append(title).append("\n");

                    for (Element link : links) {
                       *//* builder.append("\n").append("Link : ").append(link.attr("href"))
                                .append("\n").append("Text : ").append(link.text()).append("\n");*//*
                        builder.append("\n").append("Link Title : ").append(link.attr("title"))
                                .append("\n").append("Text : ").append(link.text()).append("\n\n\n");
                    }*/

                    /*Elements elements = doc.select("h2[id]");
                    String title=doc.title();
                    builder.append(title).append("\n");
                    for(Element element:elements){
                        builder.append("\n").append("h2 id : ").append(element.attr("id"))
                                .append("\n").append("h2 value : ").append(element.text()).append("\n\n\n");*/

                    Element Div=doc.select("#doc").first();
                    Element table = Div.select("table.dtable").first();
                    Elements trow = table.select("tr");
                    for(Element trd:trow){

                            Elements ttd = trd.select("th"); //select th
                            for(Element ttrd:ttd){
                                builder.append("Th :").append(ttrd.text()).append("\n");
                            }



                        Elements ttdtd = trd.select("td");//select td


                            for(Element ttrd:ttdtd){
                                builder.append("Td :").append(ttrd.text()).append("\n");
                            }

                            /*for(int i=0;i<ttd.size();i++){
                                builder.append(ttd.get(i)).append("\n");
                            }*/


                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }
}
