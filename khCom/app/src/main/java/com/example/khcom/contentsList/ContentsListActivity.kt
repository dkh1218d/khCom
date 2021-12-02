package com.example.khcom.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.khcom.R
import com.example.khcom.databinding.ActivityContentsListBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

private lateinit var rv_binding : ActivityContentsListBinding

class ContentsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_binding = DataBindingUtil.setContentView(this, R.layout.activity_contents_list)
        val ctg = intent.getStringExtra("category")
        rv_binding.categoryTitle.text = ctg
        setBoard("list", ctg)

        /*val database = Firebase.database
        val myRef = database.getReference("board")

        val now = SimpleDateFormat("yyyy-MM-dd hh:mm").format(System.currentTimeMillis())

        // child - 하위 세분화 / push - 고유 id값을 생성하며 입력
        myRef.push().setValue(
            Board_Model("첫 번째 타이틀", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png", "Hobby", "ㅇㅇ", now))
        myRef.push().setValue(
            Board_Model("두 번째 타이틀", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcJrFOR%2Fbtq65fxsT31%2F20zLG61nRvSaLldzTrpah1%2Fimg.png", "Hobby", "ㅇㅇ", now))
        myRef.push().setValue(
            Board_Model("세 번째 타이틀", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "Food", "ㅇ", now))
        myRef.push().setValue(
            Board_Model("네 번째 타이틀", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "Hobby", "ㅇㅇ", now))
        myRef.push().setValue(
            Board_Model("다섯 번째 타이틀", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png", "Food", "ㅇㅇ", now))
        */
    }

    fun setBoard(kinds : String, category : String?){
        val tran = supportFragmentManager.beginTransaction()
        val bundle = Bundle()

        when(kinds){
            "list" -> {
                val b_list = Board_list_Fragment()
                bundle.putString("category", "All")
                b_list.arguments = bundle
                tran.replace(R.id.Board_area, b_list)
            }
            "view" -> {
                val b_view = Board_view_Fragment()
                tran.setCustomAnimations(R.anim.slide_xml1, R.anim.slide_xml2, R.anim.slide_xml3, R.anim.slide_xml4)
                tran.replace(R.id.Board_area, b_view)
                tran.addToBackStack("board_view")
            }
        }
        tran.commit()
    }
}