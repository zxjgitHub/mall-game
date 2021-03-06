package com.shenyu.laikaword.module.us.setpassword;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.shenyu.laikaword.R;
import com.shenyu.laikaword.base.LKWordBaseActivity;
import com.shenyu.laikaword.model.bean.reponse.LoginReponse;
import com.shenyu.laikaword.model.bean.reponse.MsgCodeReponse;
import com.shenyu.laikaword.common.Constants;
import com.shenyu.laikaword.helper.SendMsgHelper;
import com.shenyu.laikaword.model.net.api.ApiCallback;
import com.shenyu.laikaword.model.net.retrofit.RetrofitUtils;
import com.zxj.utilslibrary.utils.IntentLauncher;
import com.zxj.utilslibrary.utils.SPUtil;
import com.zxj.utilslibrary.utils.StringUtil;
import com.zxj.utilslibrary.utils.ToastUtil;
import com.zxj.utilslibrary.utils.UIUtil;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * 设置密码获取验证码
 */
public class SetPassWordMsgCodeActivity extends LKWordBaseActivity {

    @BindView(R.id.tv_ms_code)
    TextView tvMsCode;
    @BindView(R.id.tv_send_msg_phone)
    TextView tvSendMsgPhone;
    @BindView(R.id.tv_msg_code)
    EditText tvMsgCode;
    @BindView(R.id.tv_down_time)
    TextView tvDownTime;
    String typeActivity;
    @Override
    public int bindLayout() {
        return R.layout.activity_set_pass_word_msg_code;
    }

    @Override
    public void initView() {
         typeActivity= getIntent().getStringExtra("RESERT");
        if (typeActivity!=null&&typeActivity.equals("RESERT"))
            setToolBarTitle("重置支付密码");
        else
            setToolBarTitle("设置支付密码");

    }

    @Override
    public void doBusiness(Context context) {
            LoginReponse loginReponse = (LoginReponse) SPUtil.readObject(Constants.LOGININFO_KEY);
            if (null!=loginReponse) {
            final String phone = loginReponse.getPayload().getBindPhone();
            if (StringUtil.validText(phone)) {
                tvMsCode.setText("请输入"+phone+"收到的验证码");
            }
            tvDownTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SendMsgHelper.sendMsg(bindToLifecycle(),tvDownTime,phone,"setTransactionPIN");
                }
            });
        }

        RxTextView.textChanges(tvMsgCode).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                if (charSequence.toString().length()==6){
                    retrofitUtils.addSubscription(RetrofitUtils.apiStores.validateSMSCode("setTransactionPIN", charSequence.toString()), new ApiCallback<MsgCodeReponse>() {
                        @Override
                        public void onSuccess(MsgCodeReponse model) {
                            if (model.isSuccess()) {
                                if (typeActivity != null && typeActivity.equals("RESERT")) {
                                    IntentLauncher.with(mActivity).put("typeActivity", typeActivity).put("codeToken", model.getPayload().getSMSToken()).launchFinishCpresent(SetPassWordOneActivity.class);
                                } else {
                                    IntentLauncher.with(mActivity).put("codeToken", model.getPayload().getSMSToken()).launchFinishCpresent(SetPassWordOneActivity.class);
                                }
                            }
                        }

                        @Override
                        public void onFailure(String msg) {
                            ToastUtil.showToastShort(msg);
                        }

                        @Override
                        public void onFinish() {

                        }
                    });
                }
            }
        });

    }

    @Override
    public void setupActivityComponent() {

    }

}
