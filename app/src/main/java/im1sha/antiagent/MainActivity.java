package im1sha.antiagent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView dateTextView = findViewById(R.id.dateTextViewOverall);
        DatePicker datePickerFrom = findViewById(R.id.datePickerFrom);
        DatePicker datePickerTo = findViewById(R.id.datePickerTo);

        Calendar calendar = Calendar.getInstance();

        datePickerFrom.init(
                calendar.get(Calendar.YEAR),
                0,
                1,
                (view, year, monthOfYear, dayOfMonth) ->
                        UpdateLabel(datePickerFrom, datePickerTo, dateTextView)
        );

        datePickerTo.init(
                calendar.get(Calendar.YEAR),
                8,
                1,
                (view, year, monthOfYear, dayOfMonth) ->
                        UpdateLabel(datePickerFrom, datePickerTo, dateTextView)
        );

        UpdateLabel(datePickerFrom, datePickerTo, dateTextView);
    }

    private void UpdateLabel(DatePicker datePickerFrom,
                             DatePicker datePickerTo,
                             TextView dateTextView) {
        final int yearDiff = datePickerTo.getYear() - datePickerFrom.getYear();
        final int monthDiff = datePickerTo.getMonth() - datePickerFrom.getMonth() ;
        final int daysDiff =  datePickerTo.getDayOfMonth() - datePickerFrom.getDayOfMonth();

        final boolean inFuture = yearDiff < 0
                || (yearDiff == 0 && monthDiff < 0)
                || (yearDiff == 0 && monthDiff == 0 && daysDiff < 0);

        if (inFuture) {
            dateTextView.setText("Не родился");
        }
        else {
            final int realMonthDiff = daysDiff < 0? monthDiff - 1 : monthDiff;
            final int realYearDiff = realMonthDiff < 0? yearDiff - 1 : yearDiff;
            dateTextView.setText(realYearDiff + " лет");
        }
    }
}