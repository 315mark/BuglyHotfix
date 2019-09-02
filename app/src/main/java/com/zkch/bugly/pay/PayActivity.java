package com.zkch.bugly.pay;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.jpay.unionpay.UPPay;
import com.zkch.bugly.R;
import com.zkch.bugly.base.BaseActivity;
import org.json.JSONException;
import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @BindView(R.id.testWxPay)
    Button WxPay;
    @BindView(R.id.alipay)
    Button Alipay;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_pay;
    }

    @Override
    protected void init() {
        //支付宝APP支付可以使用沙箱环境测试 https://openhome.alipay.com/platform/appDaily.htm?tab=tool
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }


    @OnClick({R.id.testWxPay, R.id.alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.testWxPay:
                testWxPay(view);
                break;

            case R.id.alipay:
                testAliPay(view);
                break;
        }
    }


    public void testWxPay(View view){
        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();

        Order order = new Order();
        order.setBody("会员充值中心");
        order.setParaTradeNo(System.currentTimeMillis()+"");
        order.setTotalFee(20);
        order.setAttach("json");//附加参数
        order.setNofityUrl("http://www.baidu.com");//支付成功服务端回调通知的地址
        order.setDeviceInfo("");

        new WXPayTask(this).execute(order);
    }

    public void testAliPay(View view){
        Toast.makeText(this, "支付宝测试", Toast.LENGTH_SHORT).show();

        Order order = new Order();
        order.setBody("会员充值中心");
        order.setParaTradeNo(System.currentTimeMillis()+"");
        order.setTotalFee(20);
        order.setAttach("json");//附加参数
        order.setNofityUrl("http://www.xxxx.com");//支付成功服务端回调通知的地址

        new AliPayTask(this).execute(order);
    }

    public void testUPPPay(View view){
        Toast.makeText(this, "银联测试", Toast.LENGTH_SHORT).show();

        Order order = new Order();
        order.setBody("会员充值中心");
        order.setParaTradeNo(System.currentTimeMillis()+"");
        order.setTotalFee(20);
        order.setAttach("json");//附加参数
        order.setNofityUrl("http://www.xxxx.com");//支付成功服务端回调通知的地址

     //   new UPPayTask(this).execute(order);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            UPPay.getInstance(this).onUUPayResult(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
