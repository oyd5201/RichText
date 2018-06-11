package com.example.richeditortest.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.richeditortest.R;
import com.example.richeditortest.view.SanJiaoView;

import java.util.concurrent.LinkedBlockingDeque;


/**
 * 浮动弹窗
 * Created by yuanht on 16/7/3.
 */
public class PopupWindowUtil {

    private static Context mContext;
    public static PopupWindow popupWindow;
    /**
     * 底部显示弹窗
     */

    /**
     * 显示弹窗
     *
     * @param location 显示位置，0-下方，1-上方，2-左边，3-右边
     */

    public static PopupWindow showPopupWindow(Context context, View view, View anchor,int location,View.OnClickListener itemsOnClick) {
        mContext = context;
        int popupWidth;
        int popupHeight;
        popupWindow = new PopupWindow(view,  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        //setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        SanJiaoView sjv = view.findViewById(R.id.sanjiaoView);
        LinearLayout linearLayout = view.findViewById(R.id.sanJiao_card);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        // 设置为true之后，PopupWindow内容区域 才可以响应点击事件
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] loc = new int[2];
        int[] locLy = new int[2];
        linearLayout.getLocationOnScreen(locLy);
        anchor.getLocationOnScreen(loc);
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = view.getHeight();
        popupWidth = view.getWidth();
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        int left = anchor.getLeft();
        int top = anchor.getTop();
        int right = anchor.getRight();

        //获取手机的分辨率
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        int dWidth = display.getWidth();
        int dHeight = display.getHeight();

        //获取popupwindow布局的padding值
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        view.measure(w, h);
        linearLayout.measure(w, h);

        //popupwindow主体的宽度
        int width1 = linearLayout.getMeasuredWidth();
        int height1 = linearLayout.getMeasuredHeight();
        //popwindow带padding的宽度
        int widthP = width1 + paddingLeft + paddingRight;

        //anchor的宽度
        int parentWidth = right - left;
        Log.d("123",parentWidth+" "+anchor.getWidth());

        //小三角的宽度
        sjv.measure(w, h);
        int width = sjv.getMeasuredWidth();
        Log.e("sjw",width+"");

        //小三角最大的marginLeft值
        int maxMarginLeft = width1 - width;

        //控件需调整的margin值
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //判断使用那种计算方式来计算小三角的位移量
        int centerIndex;
//        if (parentWidth > widthP) {
//            centerIndex = maxMarginLeft / 2;
//            Log.e("1",centerIndex+"");
//        } else if ((dWidth - left) > widthP) {
//            Log.e("dwi",dWidth+" "+left+" "+widthP+" "+"padingleft"+paddingLeft);
//           // centerIndex = parentWidth / 2 - width / 2 - paddingLeft;
//            centerIndex = (right-parentWidth/2-linearLayout.getLeft())*3;
//            Log.e("xxxx",right+"-"+parentWidth/2+"-"+linearLayout.getLeft());
//            Log.e("2",centerIndex+"");
//        } else {
//            int rWidth = dWidth - right;
//            centerIndex = width1 - (parentWidth / 2 + rWidth - paddingRight);
//            Log.e("3",centerIndex+"");
//        }
//
//        if (centerIndex > maxMarginLeft) {
//
////            centerIndex = maxMarginLeft;
////            Log.e("4",centerIndex+"");
//        }


        centerIndex = (loc[0]-locLy[0]-parentWidth/2);
        Log.e("xxxx",loc[0]+"-"+locLy[0]);
        Log.e("333",centerIndex+"");
        params.setMargins(centerIndex, 0, 0, 0);
        //sjv.setLayoutParams(params);
        switch (location) {
            case 0:
                popupWindow.showAsDropDown(anchor);
                break;
            case 1:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (loc[0] + anchor.getWidth() / 2) - popupWidth / 2, loc[1] - popupHeight-sjv.getHeight());
                break;
            case 2:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, loc[0] - popupWindow.getWidth(), loc[1]);
                break;
            case 3:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, loc[0] + view.getWidth(), loc[1]);
                break;
            case 5:
                popupWindow.showAtLocation(anchor,Gravity.BOTTOM,0,0);
                break;
        }
       popupWindow.setOnDismissListener(new poponDismissListener());



        //单击事件
        TextView mTvB = (TextView)view.findViewById(R.id.tv_b);
        ImageView mImgT = (ImageView)view.findViewById(R.id.img_t);
        ImageView mImgU = (ImageView)view.findViewById(R.id.img_u);
        LinearLayout mLyPoint = (LinearLayout) view.findViewById(R.id.ly_point);
        TextView mTvL = (TextView)view.findViewById(R.id.tv_l);

        ImageView mImaH1 = (ImageView)view.findViewById(R.id.img_h1);
        ImageView mImaH2 = (ImageView)view.findViewById(R.id.img_h2);
        ImageView mImaH3 = (ImageView)view.findViewById(R.id.img_h3);
        ImageView mImaZw = (ImageView)view.findViewById(R.id.img_zw);

        LinearLayout mLyRed = (LinearLayout)view.findViewById(R.id.ly_red);
        LinearLayout mLyOrange = (LinearLayout)view.findViewById(R.id.ly_orange);
        LinearLayout mLyYellow = (LinearLayout)view.findViewById(R.id.ly_yellow);
        LinearLayout mLyViolet = (LinearLayout)view.findViewById(R.id.ly_violet);
        LinearLayout mLyBlue = (LinearLayout)view.findViewById(R.id.ly_blue);
        LinearLayout mLyBlack = (LinearLayout)view.findViewById(R.id.ly_black);
        LinearLayout mLyWhite = (LinearLayout)view.findViewById(R.id.ly_white);
        if(mTvB!=null){
            mTvB.setOnClickListener(itemsOnClick);
            mImgT.setOnClickListener(itemsOnClick);
            mImgU.setOnClickListener(itemsOnClick);
            mLyPoint.setOnClickListener(itemsOnClick);
            mTvL.setOnClickListener(itemsOnClick);
        }
        if(mImaH1!=null){
            mImaH1.setOnClickListener(itemsOnClick);
            mImaH2.setOnClickListener(itemsOnClick);
            mImaH3.setOnClickListener(itemsOnClick);
            mImaZw.setOnClickListener(itemsOnClick);
        }
        if(mLyWhite!=null){
            mLyRed.setOnClickListener(itemsOnClick);
            mLyOrange.setOnClickListener(itemsOnClick);
            mLyYellow.setOnClickListener(itemsOnClick);
            mLyViolet.setOnClickListener(itemsOnClick);
            mLyBlue.setOnClickListener(itemsOnClick);
            mLyBlack.setOnClickListener(itemsOnClick);
            mLyWhite.setOnClickListener(itemsOnClick);
        }

        return popupWindow;
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    static class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            setBackgroundAlpha(1f);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }
}
