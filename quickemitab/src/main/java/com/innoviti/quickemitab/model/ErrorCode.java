package com.innoviti.quickemitab.model;

import java.util.HashMap;

public class ErrorCode {

  private static HashMap<String, String> mMessages;

  private static ErrorCode mInstance = null;

  public static final String ERROR_CODE_00 = "00";

  public static final String ERROR_CODE_99 = "99";


  public ErrorCode() {
    initMessages();
  }

  /**
   * This method get the message based on code
   * 
   * @param code
   * @return String
   */
  public String getMessage(String code) {
    Object obj = mMessages.get(code);
    String msg = null;
    if(obj != null)
      msg = (String) obj;
    else
      msg = "Unknown Message";
    return msg;
  }

  private void initMessages() {
    mMessages = new HashMap<String, String>(125);
    mMessages.put(ERROR_CODE_00, "Success");
    mMessages.put(ERROR_CODE_99, "Technical Error");
   
  }

  /**
   * Returns the singleton instance.
   * 
   * @return the singleton instance
   */
  public static final synchronized ErrorCode getInstance() {
    if(mInstance == null) {
      mInstance = new ErrorCode();
    }
    return mInstance;
  }

}
