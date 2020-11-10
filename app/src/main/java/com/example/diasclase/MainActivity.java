package com.example.diasclase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date initD = null;
        Date actualD = new Date(); // Fecha actual
        Date deprecatedD = null;
        try {
            initD = sd.parse("2020-11-09");
            deprecatedD = sd.parse("2020-12-21"); // Vacaciones, despues la app ya no sirve, ya que calcula a partir de la primera semana, intercalando
            //actualD = sd.parse("2020-12-20"); // Por si quieres forzar una fecha actual para comprobar que pasaria ese dia
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!(actualD.getTime() >= deprecatedD.getTime()))
        {
            TextView tvDate = findViewById(R.id.textView2);
            Locale locale = new Locale("es", "ES");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
            tvDate.setText(dateFormat.format(actualD));


            Calendar initC = new GregorianCalendar();
            Calendar actualC = new GregorianCalendar();

            initC.setTime(initD);
            actualC.setTime(actualD);

            int weeks = actualC.get(Calendar.WEEK_OF_YEAR) - initC.get(Calendar.WEEK_OF_YEAR);
            TextView tv = findViewById(R.id.textView);

            Calendar c = Calendar.getInstance();
            c.setTime(actualD);
            int day = c.get(Calendar.DAY_OF_WEEK);
            String w = "";
            if(day >= Calendar.MONDAY && day <= Calendar.FRIDAY)
            {
                w = "Esta semana";
            }
            else
            {
                weeks++;
                w = "Hoy es finde, la semana que viene ";
            }

            if (weeks % 2 == 0)
            {
                tv.setText(w + " presencial: Lunes, Miércoles y Viernes");
            }
            else
            {
                tv.setText(w + " presencial: Martes y Jueves");
            }
        }
        else
        {
            TextView tv = findViewById(R.id.textView);
            tv.setText("Esta version ya no funciona");
        }


    }
}