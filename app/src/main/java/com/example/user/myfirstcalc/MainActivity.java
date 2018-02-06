package com.example.user.myfirstcalc;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView _textV;
    private String display= " ";
    private String currentOperator= " ";
    private String result = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _textV =(TextView)findViewById(R.id.textV);
        _textV.setText(display);

    }
    private void updateScreen(){
        _textV.setText(display);
    }

    public void onClickNumber(View v){
        if(result!=""){
            clear();
            updateScreen();
        }
        Button b =(Button) v;
        display +=b.getText();
        updateScreen();
    }
    private  boolean isOperator(char op){
        switch (op){

            case '+':
            case '-':
            case '*':
            case '/':return true;
                default:return  false;

        }


    }
    public void onClickOperator(View v){
        if(display=="") return;
        Button b =(Button) v;
        if(result!="") {
           String _display = result;
            clear();
            display= _display;
        }
        if(currentOperator!=""){
            Log.d("CalX","" + display.charAt(display.length()-1));
          if (isOperator( display.charAt(display.length()-1))){
              display =display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
              updateScreen();
              return;
          }else {
              getResult();
              display =result;
              result="";

          }
            currentOperator =b.getText().toString();
        }

        display +=b.getText();
        currentOperator= b.getText().toString();
        updateScreen();
    }
    private   void  clear(){
        display= "";
        currentOperator= "";
        result="";
    }
    public   void  onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String a, String b, String op){

        switch (op) {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "*":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try{
                return Double.valueOf(a) / Double.valueOf(b);}catch (Exception e){
                    Log.d("Calc",e.getMessage());
                }
            default:
                return -1;
        }

    }
    private  boolean getResult(){
        if(currentOperator =="") return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length< 2) return false;

        result= String.valueOf(operate(operation[0], operation[1], currentOperator)) ;
        return true;

    }
    public void onClickEqual(View v){
        if(display=="")return;
        if(!getResult()) return;

        _textV.setText(display + "\n" + String.valueOf(result));

        }

}
