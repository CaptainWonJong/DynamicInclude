package captain.wonjong.dymanicinclude

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent(ll_contain, DummyData.sDummyData);
    }

    /**
     *
     * @param layout
     * @param content
     */
    private fun setContent(layout: LinearLayout, content: String) {

        if (!TextUtils.isEmpty(content)) {
            val splitContent = content.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val splitNumber = ArrayList<String>()
            val splitText = ArrayList<String>()

            // Content Text
            val mVContentView = arrayOfNulls<View>(splitContent.size)
            val mTvContentNumber = arrayOfNulls<TextView>(splitContent.size)
            val mTvContentText = arrayOfNulls<TextView>(splitContent.size)

            for (splitIdx in splitContent.indices) {
                if (TextUtils.isEmpty(splitContent[splitIdx])) {
                    splitNumber.add("")
                    splitText.add("")

                } else if (Pattern.matches("^[0-9]+$", splitContent[splitIdx].substring(0, 1))) {
                    splitNumber.add(splitContent[splitIdx].substring(0, 2))
                    splitText.add(splitContent[splitIdx].substring(2).trim { it <= ' ' })

                } else {
                    splitNumber.add("")
                    splitText.add(splitContent[splitIdx])
                }
            }

            layout.removeAllViews()

            for (layoutIdx in splitContent.indices) {
                val layoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val containView = layoutInflater.inflate(R.layout.layout_content, null)
                layout.addView(containView)

                mVContentView[layoutIdx] = containView as View

                mTvContentNumber[layoutIdx] = mVContentView[layoutIdx]!!.findViewById(R.id.tv_number) as TextView
                mTvContentText[layoutIdx] = mVContentView[layoutIdx]!!.findViewById(R.id.tv_text) as TextView

                mTvContentNumber[layoutIdx]!!.setText(splitNumber[layoutIdx])
                mTvContentText[layoutIdx]!!.setText(splitText[layoutIdx])

                if (TextUtils.isEmpty(splitNumber[layoutIdx])) {
                    mTvContentNumber[layoutIdx]!!.setVisibility(View.GONE)
                }
            }

        } else {
            // TODO: get your code!
            Log.e("ERROR!", "Content is empty!");
        }
    }
}
