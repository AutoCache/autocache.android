package com.cajuncode.autocache

import android.app.{PendingIntent, Activity, TabActivity}
import _root_.android.os.Bundle
import android.widget.TabHost
import android.content.Intent
import com.cajuncode.autocache.RadioActivity
import android.nfc.NfcAdapter

//import android.widget.TabHost.TabSpec

class HomeActivity extends TabActivity with TypedActivity {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
    val tabHost:TabHost = getTabHost()
    val mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    val mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,classOf[HomeActivity]).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0)


    val radiospec:TabHost#TabSpec  = tabHost.newTabSpec("Radio")
    // setting Title and Icon for the Tab
    radiospec.setIndicator("Radio", null)
    val radioIntent:Intent = new Intent(this, classOf[RadioActivity])
    radiospec.setContent(radioIntent)
    tabHost.addTab(radiospec)

    val msgspec:TabHost#TabSpec  = tabHost.newTabSpec("Messages")
    // setting Title and Icon for the Tab
    msgspec.setIndicator("Message", null)
    val msgIntent:Intent = new Intent(this, classOf[MessagesActivity])
    msgspec.setContent(msgIntent)
    tabHost.addTab(msgspec)

  }
  override onNewIntent(intent:Intent){
    super.onNewIntent(intent);
    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
      messages = null;
      rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
      if (rawMsgs != null) {
        messages = new NdefMessage[rawMsgs.length];
        for (int i = 0; i < rawMsgs.length; i++) {
          messages[i] = (NdefMessage) rawMsgs[i];
        }
      }
      if(messages[0] != null) {
        String result="";
        byte[] payload = messages[0].getRecords()[0].getPayload();
        // this assumes that we get back am SOH followed by host/code
        for (int b = 1; b<payload.length; b++) { // skip SOH
          result += (char) payload[b];
        }
        Toast.makeText(getApplicationContext(), "Tag Contains " + result, Toast.LENGTH_SHORT).show();
      }
    }
  }


}
