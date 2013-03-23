package com.cajuncode.autocache.tests

import com.cajuncode.autocache._
import junit.framework.Assert._
import _root_.android.test.AndroidTestCase
import _root_.android.test.ActivityInstrumentationTestCase2

class AndroidTests extends AndroidTestCase {
  def testPackageIsCorrect() {
    assertEquals("com.cajuncode.autocache", getContext.getPackageName)
  }
}

class ActivityTests extends ActivityInstrumentationTestCase2(classOf[HomeActivity]) {
   def testHelloWorldIsShown() {
      val activity = getActivity
      val textview = activity.findView(TR.textview)
      assertEquals(textview.getText, "hello, world!")
    }
}
