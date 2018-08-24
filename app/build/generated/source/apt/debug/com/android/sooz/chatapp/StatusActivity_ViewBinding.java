// Generated code from Butter Knife. Do not modify!
package com.android.sooz.chatapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StatusActivity_ViewBinding implements Unbinder {
  private StatusActivity target;

  private View view2131165301;

  private View view2131165299;

  private View view2131165300;

  @UiThread
  public StatusActivity_ViewBinding(StatusActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StatusActivity_ViewBinding(final StatusActivity target, View source) {
    this.target = target;

    View view;
    target.mEditText = Utils.findRequiredViewAsType(source, R.id.setStatus, "field 'mEditText'", EditText.class);
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.setOnline, "method 'setOnline'");
    view2131165301 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setOnline();
      }
    });
    view = Utils.findRequiredView(source, R.id.setAway, "method 'setAway'");
    view2131165299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setAway();
      }
    });
    view = Utils.findRequiredView(source, R.id.setOffline, "method 'setOffline'");
    view2131165300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setOffline();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    StatusActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEditText = null;
    target.list = null;

    view2131165301.setOnClickListener(null);
    view2131165301 = null;
    view2131165299.setOnClickListener(null);
    view2131165299 = null;
    view2131165300.setOnClickListener(null);
    view2131165300 = null;
  }
}
