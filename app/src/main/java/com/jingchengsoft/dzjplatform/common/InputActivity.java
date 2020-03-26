package com.jingchengsoft.dzjplatform.common;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.blankj.utilcode.util.StringUtils;
import com.hjq.widget.layout.SettingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/24
 * desc   :  上传数据基类
 */
public abstract class InputActivity extends MyActivity {
    public List<Integer> requiredFieldList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {
    }

    protected abstract void initRequiredField();

    /**
     * 检查所有必填项是否已经填写
     */
    public boolean checkAllRequiredField(List<Integer> requiredFieldList) {
        for (Integer id: requiredFieldList) {
            SettingBar settingBar = findViewById(id);
            if(StringUtils.isEmpty(settingBar.getRightText().toString())) {
                toast(settingBar.getLeftText().toString().replace("*", "")+"不能为空！");
                return false;
            }
        }
        return true;
    }

    /**
     * 在文字描述末尾添加红色*并且添加到必填列表中
     */
    public void addRequiredField(SettingBar settingBar) {
        //在文字结尾添加红色星号
        String s = settingBar.getLeftText().toString();
        SpannableString spannableString = new SpannableString(s+"*");
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), s.length(), s.length()+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        settingBar.setLeftText(spannableString);
        //添加到列表中
        requiredFieldList.add(settingBar.getId());
    }
}
