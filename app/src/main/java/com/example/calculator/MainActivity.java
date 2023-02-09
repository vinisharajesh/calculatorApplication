package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1,textView;

    MaterialButton btnC, brac1,brac2;
    MaterialButton div,btnX,add,sub,equ;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton Buttondot,ButtonAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textView1);
        textView=findViewById(R.id.textView);
        assignId(btnC,R.id.btnC);
        assignId(brac1,R.id.brac1);
        assignId(brac2,R.id.brac2);
        assignId(div,R.id.div);
        assignId(btnX,R.id.btnX);
        assignId(add,R.id.Add);
        assignId(sub,R.id.Sub);
        assignId(equ,R.id.equ);
        assignId(Buttondot,R.id.dot);
        assignId(ButtonAC,R.id.AC);
        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn7);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);


    }

    void assignId(MaterialButton btn,int id)
    {
        btn= findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate = textView.getText().toString();

        if(buttonText.equals("AC"))
        {
            textView.setText(" ");
            textView1.setText("0");
            return;
        }

        if(buttonText.equals("="))
        {
            textView.setText(textView1.getText());
            return;
        }

        if(buttonText.equals("C"))
        {
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else
        {
            dataToCalculate = dataToCalculate + buttonText;
        }

        textView.setText(dataToCalculate);
        String finalResult= getResult( dataToCalculate);

        if(!(finalResult.equals("Err")))
        {
            textView1.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try
        {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null ).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult= finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "Err";
        }
    }

}