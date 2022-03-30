package com.jufaja.inyedashares

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.jufaja.inyedashares.models.DataFund
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.activity_refresh_data.*


private const val TAG = "RefreshDataActivity"

class RefreshDataActivity : AppCompatActivity() {

    lateinit var tvfundnamea: TextView
    lateinit var etfundnamechange: EditText
    lateinit var tvvaluea: TextView
    lateinit var etvaluea: EditText
    lateinit var tvamountaxyz: TextView
    lateinit var tvamountax: TextView
    lateinit var tvamountay: TextView
    lateinit var tvamountaz: TextView
    lateinit var etamountax:EditText
    lateinit var etamountay:EditText
    lateinit var etamountaz:EditText
    lateinit var clipboardManager: ClipboardManager

    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var datafundz: MutableList<DataFund>
    private lateinit var adapterDataFund: DataFundsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_data)

        datafundz = mutableListOf()
        adapterDataFund = DataFundsAdapter(this, datafundz)
        rvdatafunds.adapter = adapterDataFund
        rvdatafunds.layoutManager = LinearLayoutManager(this)

        firestoreDb = FirebaseFirestore.getInstance()
        val datafundsReference = firestoreDb
            .collection("datafunds")
            .limit(1)
        datafundsReference.addSnapshotListener { snapshot, exepction ->
            if (exepction != null || snapshot == null) {
                Log.e(TAG, "exeption when qeurying datafunds")
                return@addSnapshotListener
            }
            val dataFundsList = snapshot.toObjects(DataFund::class.java)
            datafundz.clear()
            datafundz.addAll(dataFundsList)
            adapterDataFund.notifyDataSetChanged()
            for (datafunds in dataFundsList) {
                Log.i(TAG, "datafunds ${datafunds}")
            }
        }

        tvfundnamea = findViewById(R.id.tvfundnamea)
        etfundnamechange = findViewById(R.id.etfundnamechangea)
        tvvaluea = findViewById(R.id.tvvaluea)
        etvaluea= findViewById(R.id.etvaluea)
        tvamountaxyz= findViewById(R.id.tvamountaxyz)
        etamountax = findViewById(R.id.etamountax)
        etamountay= findViewById(R.id.etamountay)
        etamountaz= findViewById(R.id.etamountaz)
        tvamountax= findViewById(R.id.tvamountax)
        tvamountay= findViewById(R.id.tvamountay)
        tvamountaz= findViewById(R.id.tvamountaz)

    }
    fun copypasteAllA(view: View) {
        copyPasteNameA()
        //sumXYZ()
        samXYZ()
    }

    fun copyPasteNameA() {
        val namea = etfundnamechange.text.toString()
        val valuea = etvaluea.text.toString()
        val amountax = etamountax.text.toString()
        val amountay = etamountay.text.toString()
        val amountaz = etamountaz.text.toString()

        if (namea.isNotEmpty() || valuea.isNotEmpty() || amountax.isNotEmpty() ||
            amountay.isNotEmpty() || amountaz.isNotEmpty()) {
            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataOne = ClipData.newPlainText("key", namea)
            clipboardManager.setPrimaryClip(clipDataOne)
            val clipDataSec: ClipData = clipboardManager.primaryClip!!
            val itemOne: ClipData.Item = clipDataSec.getItemAt(0)
            tvfundnamea.text = itemOne.text.toString()

            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataTri = ClipData.newPlainText("key", valuea)
            clipboardManager.setPrimaryClip(clipDataTri)
            val clipDataFou: ClipData = clipboardManager.primaryClip!!
            val itemTwo: ClipData.Item = clipDataFou.getItemAt(0)
            tvvaluea.text = itemTwo.text.toString()

            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataFiv = ClipData.newPlainText("key", amountax)
            clipboardManager.setPrimaryClip(clipDataFiv)
            val clipDataSix: ClipData = clipboardManager.primaryClip!!
            val itemTri: ClipData.Item = clipDataSix.getItemAt(0)
            tvamountax.text = itemTri.text.toString()

            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataSev = ClipData.newPlainText("key", amountay)
            clipboardManager.setPrimaryClip(clipDataSev)
            val clipDataEig: ClipData = clipboardManager.primaryClip!!
            val itemFou: ClipData.Item = clipDataEig.getItemAt(0)
            tvamountay.text = itemFou.text.toString()

            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataNin = ClipData.newPlainText("key", amountaz)
            clipboardManager.setPrimaryClip(clipDataNin)
            val clipDataTen: ClipData = clipboardManager.primaryClip!!
            val itemFiv: ClipData.Item = clipDataTen.getItemAt(0)
            tvamountaz.text = itemFiv.text.toString()
            Toast.makeText(this, "Info. Copy/paste succesfull", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(
                this, "Info. S.W.W.", Toast.LENGTH_LONG).show()
        }
    //}
    //private fun sumXYZ() {
    //    val amountax = tvamountax.text.toString()
    //    val amountay = tvamountay.text.toString()
    //    val amountaz = tvamountaz.text.toString()
    //    if (amountax.isNotEmpty() || amountay.isNotEmpty() || amountaz.isNotEmpty()) {
    //        val amountaxyz = amountax.toDouble() + amountay.toDouble() + amountaz.toDouble()
    //        tvamountaxyz.text = "%.4f".format(amountaxyz)

    //    } else {
    //        Toast.makeText(this, "Error. S.W.W", Toast.LENGTH_SHORT).show()
    //    }
    }
    private fun samXYZ(): Map<String, Any> {
        val namea = tvfundnamea.text.toString();
        val valuea = tvvaluea.text.toString()
        val amountax = tvamountax.text.toString();
        val amountay = tvamountay.text.toString()
        val amountaz = tvamountaz.text.toString();
        val newWrap = mutableMapOf<String, Any>()

        if (namea.isNotEmpty()) {
            newWrap["Field 1"] = namea
        }
        if (valuea.isNotEmpty()) {
            newWrap["Field 2"] = valuea
        }
        if (amountax.isNotEmpty()) {
            newWrap["Field 3"] = amountax
        }
        if (amountay.isNotEmpty()) {
            newWrap["Field 4"] = amountay
        }
        if (amountaz.isNotEmpty()) {
            newWrap["Field 5"] = amountaz
            //}
            //run {
            //    val amountaxyz = amountax.toDouble() + amountay.toDouble() + amountaz.toDouble()
            //        tvamountaxyz.text = "%.4f".format(amountaxyz)
        }
        for (entertedInFields in newWrap) {
            Log.i(TAG, "entry--> $entertedInFields")
        }
        val somX : String? = newWrap["Field 3"] as String?
        val somY : String? = newWrap["Field 4"] as String?
        val somZ : String? = newWrap["Field 5"] as String?
        if (somX != null) {
            if (somY != null) {
                if (somZ != null) {
            val somXYZ = ((somX.toDouble() + somY.toDouble() + somZ.toDouble()).toString())
            tvamountaxyz.text = (somXYZ)
                }else{
                    Toast.makeText(this, "Amount partys can't be empty.\n" +
                            "          Use default value 0", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Amount partys can't be empty.\n" +
                        "          Use default value 0", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "Amount partys can't be empty.\n" +
                    "          Use default value 0", Toast.LENGTH_LONG).show()
        }
        return newWrap
    }
}
