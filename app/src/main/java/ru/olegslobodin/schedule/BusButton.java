package ru.olegslobodin.schedule;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Intent;

public class BusButton extends Button {
    BusButton(android.content.Context context, int BusNumber, int pos, RelativeLayout relativeLayout) {
        super(context);
        number = BusNumber;
        position = pos;
        this.setText(String.valueOf(number));
        int size = (int) ((relativeLayout.getWidth() - relativeLayout.getPaddingLeft() - relativeLayout.getPaddingRight()) / ((1 + whiteSpace) * countOnRow + whiteSpace));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.width = size;
        params.height = size;
        params.leftMargin += (int) ((whiteSpace + (1 + whiteSpace) * ((position - 1) % countOnRow)) * size);
        params.topMargin += (int) ((whiteSpace + (1 + whiteSpace) * ((position - 1) / countOnRow)) * size);
        this.setLayoutParams(params);

        /*
        System.out.println("________________________________________\nNumber " + number);
        System.out.println("size " + size);
        System.out.println("left " + params.leftMargin);
        System.out.println("top " + params.topMargin);
        */
    }

    static final int countOnRow = 5;
    static final double whiteSpace = 0.0;   //of button width
    int number;
    int position;
}
