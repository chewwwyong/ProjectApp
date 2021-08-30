package com.example.projectapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectapp.Tools.Article;
import com.example.projectapp.Tools.GetData;
import com.example.projectapp.Tools.OkHttpUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txv_showData, txv_warningMsg;

    final static String url = "http://140.125.45.214:3000/result.html";
    final static String TAG = "MainActivity";

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage: "+ "爬结束");

            switch (msg.what) {
                case 1:
                    Log.i(TAG, "handleMessage: "+ "開始展示數據");
                    ArrayList<Article> articles = (ArrayList<Article>)msg.obj;
                    Log.i(TAG, "handleMessage:articles.size() "+ articles.size());

                    for (Article item:articles) {
                        Log.i(TAG, "handleMessage: "+ item.toString());
                        data_processing(item, articles.size());
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv_showData = findViewById(R.id.txv_showData);
        txv_warningMsg = findViewById(R.id.txv_warningMsg);
        Test();
    }

    private void Test(){
        new Thread(() -> {
            while (true){
                try {
                    String html = OkHttpUtils.OkGetArt(url);
                    ArrayList<Article> articles = GetData.spiderArticle(html);
                    //发送信息给handler用于更新UI界面
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = articles;
                    handler.sendMessage(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void data_processing(Article item, int data_size){
        ArrayList<String> timestamp = new ArrayList<>();
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> v_id = new ArrayList<>();
        ArrayList<String> Speed = new ArrayList<>();
        ArrayList<String> Speed_State = new ArrayList<>();
        ArrayList<String> Tire_Pressure = new ArrayList<>();
        ArrayList<String> Tire_Pressure_State = new ArrayList<>();
        ArrayList<String> Change_Lane_State = new ArrayList<>();
        ArrayList<String> Driving_State = new ArrayList<>();
        ArrayList<String> ACC_State = new ArrayList<>();
        ArrayList<String> LDW_State = new ArrayList<>();
        ArrayList<String> BSM_State = new ArrayList<>();
        ArrayList<String> Lane_State = new ArrayList<>();
        ArrayList<String> Road_State = new ArrayList<>();

        String result = item.toString();
        //     timestamp: 2021/08/30 11:14:20, key: 101, column: Vehicle ID:highwayCD4.0, value: 3
        //     timestamp: 2021/08/30 11:14:55, key: 1017, column: Vehicle ID:highwayAB1.24, value: 9

        String[] tmpstr = result.split(",");
        for(int i=0;i<tmpstr.length;i++){

            //Toast.makeText(MainActivity.this, tmpstr[i].toString(), Toast.LENGTH_SHORT).show();

            // 抓timestamp
            if( tmpstr[i].toString().startsWith("timestamp: ")){
                // timestamp: 2021/08/30 11:14:55
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = timestamp
                // str_split[1] = 2021/08/30 11:14:55
                timestamp.add(str_split[1]);
                //Toast.makeText(MainActivity.this, timestamp.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓key
            else if( tmpstr[i].toString().startsWith(" key: ")){
                // key: 1017
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = key
                // str_split[1] = 1017
                key.add(str_split[1]);
                //Toast.makeText(MainActivity.this, key.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓v_id
            else if( tmpstr[i].toString().startsWith(" value: v.id: ")){
                //  value: v.id: highwayDA5.0
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = value
                // str_split[1] = v.id
                // str_split[2] = highwayDA5.0
                v_id.add(str_split[2]);
                //Toast.makeText(MainActivity.this, v_id.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Speed
            else if( tmpstr[i].toString().startsWith(" Speed: ")){
                //  Speed: 0.0 km/h (0.00m/s)
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Speed
                // str_split[1] = 0.0 km/h (0.00m/s)
                Speed.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Speed.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Speed_State
            else if( tmpstr[i].toString().startsWith(" Speed_State: ")){
                //  Speed_State: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Speed_State
                // str_split[1] = G
                Speed_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Speed_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Tire_Pressure
            else if( tmpstr[i].toString().startsWith(" Tire_Pressure: ")){
                //  Tire_Pressure: 33 psi
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Tire_Pressure
                // str_split[1] = 33 psi
                Tire_Pressure.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Tire_Pressure.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Tire_Pressure_State
            else if( tmpstr[i].toString().startsWith(" Tire_Pressure_State: ")){
                // Tire_Pressure_State: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Tire_Pressure_State
                // str_split[1] = G
                Tire_Pressure_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Tire_Pressure_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Change_Lane_State
            else if( tmpstr[i].toString().startsWith(" Change_Lane_State: ")){
                // Change_Lane_State: R
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Change_Lane_State
                // str_split[1] = R
                Change_Lane_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Change_Lane_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓Driving_State
            else if( tmpstr[i].toString().startsWith(" Driving_State: ")){
                //  Driving_State: hostile brake warning!!!
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Driving_State
                // str_split[1] = hostile brake warning!!!
                Driving_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Driving_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓ACC_State
            else if( tmpstr[i].toString().startsWith(" ACC_State: ")){
                //  ACC_State: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = ACC_State
                // str_split[1] = G
                ACC_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, ACC_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // 抓ACC_State
            else if( tmpstr[i].toString().startsWith(" LDW_State: ")){
                // LDW_State: Left: G Right: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = LDW_State
                // str_split[1] = Left: G Right: G
                LDW_State.add(str_split[1] + ":" + str_split[2] + ":" + str_split[3]);
                //Toast.makeText(MainActivity.this, LDW_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // BSM_State
            else if( tmpstr[i].toString().startsWith(" BSM_State: ")){
                // BSM_State: Left: G Right: G Center: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = BSM_State
                // str_split[1] = Left: G Right: G Center: G
                BSM_State.add(str_split[1] + ":" + str_split[2] + ":" + str_split[3] + ":" + str_split[4]);
                //Toast.makeText(MainActivity.this, BSM_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // Lane_State
            else if( tmpstr[i].toString().startsWith(" Lane_State: ")){
                // Lane_State: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Lane_State
                // str_split[1] = G
                Lane_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Lane_State.get(0), Toast.LENGTH_SHORT).show();
            }

            // Road_State
            else if( tmpstr[i].toString().startsWith(" Road_State: ")){
                // Road_State: G
                String[] str_split = tmpstr[i].split(": ");
                // str_split[0] = Road_State
                // str_split[1] = G
                Road_State.add(str_split[1]);
                //Toast.makeText(MainActivity.this, Road_State.get(0), Toast.LENGTH_SHORT).show();
            }
        }

        // 一筆一筆資料抓
        for(int i=0;i<v_id.size();i++){
            txv_showData.setText("TimeStamp： " + timestamp.get(i) +
                            "\nkey： " + key.get(i) +
                            "\nv_id： " + v_id.get(i) +
                            "\nSpeed： " + Speed.get(i) +
                            "\nSpeed_State： " + Speed_State.get(i) +
                            "\nTire_Pressure： " + Tire_Pressure.get(i) +
                            "\nTire_Pressure_State： " + Tire_Pressure_State.get(i) +
                            "\nChange_Lane_State： " + Change_Lane_State.get(i) +
                            "\nDriving_State： " + Driving_State.get(i) +
                            "\nACC_State： " + ACC_State.get(i) +
                            "\nLDW_State： " + LDW_State.get(i) +
                            "\nBSM_State： " + BSM_State.get(i) +
                            "\nLane_State： " + Lane_State.get(i) +
                            "\nRoad_State： " + Road_State.get(i) + "\n");

            // 只要有任意State有R 那就顯示Warning
            if(Speed_State.get(i).equals("R") ||
                    Tire_Pressure_State.get(i).equals("R") ||
                    Change_Lane_State.get(i).equals("R")||
                    Driving_State.get(i).equals("R") ||
                    ACC_State.get(i).equals("R") ||
                    LDW_State.get(i).equals("R") ||
                    BSM_State.get(i).equals("R") ||
                    Lane_State.get(i).equals("R") ||
                    Road_State.get(i).equals("R")){
                txv_warningMsg.setText("Warning!!");
                txv_warningMsg.setTextColor(Color.RED);
            }
            else{
                txv_warningMsg.setText("Detecting...");
                txv_warningMsg.setTextColor(Color.BLACK);
            }
        }
    }
}