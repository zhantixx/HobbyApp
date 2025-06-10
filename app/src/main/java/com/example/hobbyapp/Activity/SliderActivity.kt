package com.example.hobbyapp.Activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.hobbyapp.Adapter.SliderAdapter
import com.example.hobbyapp.R
import com.example.hobbyapp.Domain.SliderModel

class SliderActivity : AppCompatActivity() {

    private lateinit var adapter: SliderAdapter
    private lateinit var skip: Button
    private var size = 0
    private lateinit var viewPager: ViewPager
    private lateinit var dotsLL: LinearLayout
    private lateinit var sliderModalArrayList: ArrayList<SliderModel>
    private lateinit var dots: Array<TextView?>

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            addDots(size, position)

            if (position == 2) {
                Handler().postDelayed({
                    val intent = Intent(this@SliderActivity, IntroActivity::class.java)
                    intent.putExtra("linkss", "https://hssakingamapp.blogspot.com/")
                    startActivity(intent)
                    finish()
                }, 1000)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)



        skip = findViewById(R.id.idBtnSkip)
        skip.setOnClickListener {
            val intent = Intent(this@SliderActivity, IntroActivity::class.java)
            intent.putExtra("linkss", "https://hssakingamapp.blogspot.com/")
            startActivity(intent)
            finish()
        }

        viewPager = findViewById(R.id.idViewPager)
        dotsLL = findViewById(R.id.idLLDots)
        sliderModalArrayList = ArrayList()

        sliderModalArrayList.add(
            SliderModel(
                "Qosh keldiniz!",
                " HobbyApp – sizdiń jaña qyzyǵushylyqtar alemi!",
                R.drawable.intro2
            )
        )

        sliderModalArrayList.add(
            SliderModel(
                "Hobbıńdy ash!",
                "Surét salý, kod jazý, as dıiındau – barı osynda!",
                R.drawable.intro3
            )
        )

        sliderModalArrayList.add(
            SliderModel(
                "Jańa dostardy tap!",
                " Hobbiler arqyly ózıńe úqsas dostardy tap!",
                R.drawable.intro4
            )
        )


        adapter = SliderAdapter(this, sliderModalArrayList)
        viewPager.adapter = adapter

        size = sliderModalArrayList.size
        addDots(size, 0)
        viewPager.addOnPageChangeListener(viewListener)
    }

    private fun addDots(size: Int, pos: Int) {
        dots = arrayOfNulls(size)
        dotsLL.removeAllViews()

        for (i in 0 until size) {
            dots[i] = TextView(this).apply {
                text = "."
                textSize = 35f
                setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.black))
            }
            dotsLL.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[pos]?.setTextColor(ContextCompat.getColor(this, R.color.purple_200))
        }
    }
}
