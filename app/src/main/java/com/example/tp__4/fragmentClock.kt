package com.example.tp__4
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

private const val IS_DIGITAL = "digitalOK"
class FragmentClock : Fragment() {
    // TODO: Rename and change types of parameters
    private var digitalClock = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            digitalClock = it.getBoolean(IS_DIGITAL)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
         if (!digitalClock) {
            view = inflater.inflate(R.layout.frag_numeric, container, false)

            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            // Assuming you have TextViews for hour and minute in frag_numeric layout
            var hourTextView: TextView = view.findViewById(R.id.hour_value_text_view)
            var minuteTextView: TextView = view.findViewById(R.id.minute_value_text_view)
            hourTextView.text= String.format("%02d", hour)
            minuteTextView.text = String.format("%02d", minute)

        }
        else
            view=inflater.inflate(R.layout.frag_digital, container, false)

        return view
    }
}

