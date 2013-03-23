/**
 * Created with IntelliJ IDEA.
 * User: alley
 * Date: 3/23/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */

package com.cajuncode.autocache

import _root_.android.app.Activity
import _root_.android.os.Bundle

class RadioActivity extends Activity with TypedActivity {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.radio)


  }
}
