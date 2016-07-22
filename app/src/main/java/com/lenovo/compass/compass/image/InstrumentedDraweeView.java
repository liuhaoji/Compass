/*
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only.  Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.lenovo.compass.compass.image;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * {@link SimpleDraweeView} with instrumentation.
 */
public class InstrumentedDraweeView extends SimpleDraweeView {


    public InstrumentedDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public InstrumentedDraweeView(Context context) {
        super(context);
        init();
    }

    public InstrumentedDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InstrumentedDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
    }


    @Override
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
    }

//  @Override
//  public void setImageURI(Uri uri, @Nullable Object callerContext) {
//    super.setImageURI(uri);
//
//  }
//
//  public ControllerListener<Object> getListener() {
//    return mListener;
//  }
}
