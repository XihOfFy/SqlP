package xih.fy.sqlp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*sql test*/
public class MainActivity extends Activity {
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 0:
                    Toast.makeText(MainActivity.this,"数据库连接失败",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(MainActivity.this,"连接成功！！！",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    });
    static String url = "jdbc:mysql://192.168.0.102:3306/test?&characterEncoding=utf8";

    static String username = "root";

    static String password = "admin";

    static Connection conn = null;

    static ResultSet rs = null;

    static PreparedStatement ps =null;
    private EditText et;
    private Button bt;
    private TextView tv;
    private Button z;
    private Button s;
    private Button g;
    private Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        bt=findViewById(R.id.bt);
        tv=findViewById(R.id.tv);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                if (conn==null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        consql();
                    }
                }).start();
                }
            }
        });
        zsgc();
    }

    public void consql(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
           handler.sendEmptyMessage(1);

        } catch (Exception e) {
            handler.sendEmptyMessage(0);

            e.printStackTrace();
        }
    }

    public void zsgc(){
        z=findViewById(R.id.z);
        s=findViewById(R.id.s);
        g=findViewById(R.id.g);
        c=findViewById(R.id.c);
        z.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
                try
                {
                    Statement state=conn.createStatement();
                    String sql="insert into test values(20,'john','newyear','123')";   //SQL语句
                    state.executeUpdate(sql);         //将sql语句上传至数据库执行
                }
                catch (SQLException e)
                {}   //容器

            }
        });
        s.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
            }

        });
        g.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
            }


        });
        c.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                // TODO: Implement this method
            }


        });
    }
}
