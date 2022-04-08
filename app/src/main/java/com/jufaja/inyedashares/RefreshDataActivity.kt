package com.jufaja.inyedashares

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.activity_refresh_data.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "RefreshDataActivity"
class RefreshDataActivity : AppCompatActivity() {

    lateinit var tvfundnamea: TextView
    lateinit var etfundnamechange: EditText
    lateinit var tvvaluea: TextView
    lateinit var etvaluea: EditText
    lateinit var tvinlaya: TextView
    lateinit var etinlaya: EditText
    lateinit var tvamountaxyz: TextView
    lateinit var tvamountax: TextView
    lateinit var tvamountay: TextView
    lateinit var tvamountaz: TextView
    lateinit var etamountax: EditText
    lateinit var etamountay: EditText
    lateinit var etamountaz: EditText

    lateinit var tvastotalfunda: TextView
    lateinit var tvatmultigrowa: TextView
    lateinit var tvautotalgrowa: TextView
    lateinit var tvavmultiperca: TextView
    lateinit var tvawtotalperca: TextView
    lateinit var tvacpartyfunda: TextView
    lateinit var tvadpartygrowa: TextView
    lateinit var tvaepartyperca: TextView

    lateinit var clipboardManager: ClipboardManager
    private lateinit var firestoreDb: FirebaseFirestore
    private val datafundsCollectionRef = Firebase.firestore.collection("datafunds")
    private lateinit var datafundz: MutableList<DataFund>
    private lateinit var adapterDataFund: DataFundsAdapter
    private lateinit var olddataa: MutableList<DataFund>
    private lateinit var adapterOldData: AOldDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_data)
        firstAToRecyclerView()
        oldDataDystrybutor(1, "inlay", R.id.tvinlayold, "valuez", R.id.tvvalueaold,
            "partyfundz", R.id.tvamountaxyzold, "totalfundz", R.id.tvtotalfundaold,
            "multigrowz", R.id.tvmultigrowaold, "totalgrowz", R.id.tvtotalgrowaold,
            "multipercz", R.id.tvmultipercaold, "totalpercz", R.id.tvtotalpercaold,
            "partygrowz", R.id.tvpartygrowaold, "partypercz", R.id.tvpartypercaold)

        //secAToRecyclerView()

        tvfundnamea = findViewById(R.id.tvfundnamea)
        etfundnamechange = findViewById(R.id.etfundnamechangea)
        tvvaluea = findViewById(R.id.tvvaluea)
        etvaluea = findViewById(R.id.etvaluea)
        tvinlaya = findViewById(R.id.tvinlaya)
        etinlaya = findViewById(R.id.etinlaya)
        tvamountaxyz = findViewById(R.id.tvamountaxyz)
        etamountax = findViewById(R.id.etamountax)
        etamountay = findViewById(R.id.etamountay)
        etamountaz = findViewById(R.id.etamountaz)
        tvamountax = findViewById(R.id.tvamountax)
        tvamountay = findViewById(R.id.tvamountay)
        tvamountaz = findViewById(R.id.tvamountaz)

        tvastotalfunda = findViewById(R.id.tvastotalfunda)
        tvatmultigrowa = findViewById(R.id.tvatmultigrowa)
        tvautotalgrowa = findViewById(R.id.tvautotalgrowa)
        tvavmultiperca = findViewById(R.id.tvavmultiperca)
        tvawtotalperca = findViewById(R.id.tvawtotalperca)
        tvacpartyfunda = findViewById(R.id.tvacpartyfunda)
        tvadpartygrowa = findViewById(R.id.tvadpartygrowa)
        tvaepartyperca = findViewById(R.id.tvaepartyperca)

        swcalculatinga.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                swcalculatinga.text = resources.getString(R.string.clicker_Calculate)
                swcalculatinga.setTextColor(resources.getColor(R.color.oldtext))

                calculateAmA(etfundnamechange, tvfundnamea, "Name is")
                calculateAmA(etvaluea, tvvaluea, "Value is")
                calculateAmA(etinlaya, tvinlaya, "Inlay is")
                calculateAmA(etamountax, tvamountax, "Amount partys can't be")
                calculateAmA(etamountay, tvamountay, "Amount partys can't be")
                calculateAmA(etamountaz, tvamountaz, "Amount partys can't be")

                sumXYZ(tvamountax, tvamountay, tvamountaz, tvamountaxyz)
                wrapToFbase(tvfundnamea, tvvaluea, tvamountaxyz, tvinlaya)

                caluTotalValueFundA(tvvaluea, tvamountaxyz, tvastotalfunda)

                btnfirebasea.setTextColor(resources.getColor(R.color.oldtext))
            } else {
                swcalculatinga.text = resources.getString(R.string.clicker_De_Calculate)
                swcalculatinga.setTextColor(resources.getColor(R.color.transparent_color))
                tvfundnamea.text = ""
                tvvaluea.text = ""
                tvinlaya.text = ""
                tvamountaxyz.text = ""
                tvamountax.text = ""
                tvamountay.text = ""
                tvamountaz.text = ""

                tvastotalfunda.text = ""

                btnfirebasea.setTextColor(resources.getColor(R.color.transparent_color))
                //btnfirebasea.isEnabled = false
                Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
            }
            btnfirebasea.setOnClickListener {
                switchData(1, wrapToFbase(tvfundnamea, tvvaluea, tvinlaya, tvamountaxyz))
                //caluTest123()
            }
        }
        btntest123.setOnClickListener {
            /*oldDataDystrybutor(1, "inlay", R.id.tvinlayold, "valuez", R.id.tvvalueaold,
                "partyfundz", R.id.tvamountaxyzold, "totalfundz", R.id.tvtotalfundaold,
                "multigrowz", R.id.tvmultigrowaold, "totalgrowz", R.id.tvtotalgrowaold,
                "multipercz", R.id.tvmultipercaold, "totalpercz", R.id.tvtotalpercaold,
                "partygrowz", R.id.tvpartygrowaold, "partypercz", R.id.tvpartypercaold)
         */}
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun firstAToRecyclerView() {
        datafundz = mutableListOf()
        adapterDataFund = DataFundsAdapter(this, datafundz)
        rvdatafunds.adapter = adapterDataFund
        rvdatafunds.layoutManager = LinearLayoutManager(this)

        firestoreDb = FirebaseFirestore.getInstance()
        val datafundsReference = firestoreDb
            .collection("datafunds")
            .whereEqualTo("numberz", 1)
            //.limit(1)
        datafundsReference.addSnapshotListener { snapshot, exepction ->
            if (exepction != null || snapshot == null) {
                Log.e(TAG, "exeption when qeurying datafunds")
                return@addSnapshotListener
            }
            val dataFundsList = snapshot.toObjects<DataFund>()
            datafundz.clear()
            datafundz.addAll(dataFundsList)
            adapterDataFund.notifyDataSetChanged()
        }
    }
    /*@SuppressLint("NotifyDataSetChanged")
    private fun secAToRecyclerView() {
        olddataa = mutableListOf()
        adapterOldData = AOldDataAdapter(this, datafundz)
        rvolddataa.adapter = adapterOldData
        rvolddataa.layoutManager = LinearLayoutManager(this)

        firestoreDb = FirebaseFirestore.getInstance()
        val datafundsReference = firestoreDb
            .collection("datafunds")
            .whereEqualTo("numberz", 1)
            //.limit(1)
        datafundsReference.addSnapshotListener { snapshot, exepction ->
            if (exepction != null || snapshot == null) {
                Log.e(TAG, "exeption when qeurying datafunds")
                return@addSnapshotListener
            }
            val dataFundsList = snapshot.toObjects<DataFund>()
            olddataa.clear()
            olddataa.addAll(dataFundsList)
            adapterOldData.notifyDataSetChanged()
        }
    }*/
    private fun sumXYZ(amountx: TextView, amounty: TextView, amountz: TextView, amountXYZ: TextView)
                        : Map<String, Any> {
        val amountax = amountx.text.toString()
        val amountay = amounty.text.toString()
        val amountaz = amountz.text.toString();
        val wrapXYZ = mutableMapOf<String, Any>()
        if (amountax.isNotEmpty()) {
            wrapXYZ["Field X"] = amountax
        }
        if (amountay.isNotEmpty()) {
            wrapXYZ["Field Y"] = amountay
        }
        if (amountaz.isNotEmpty()) {
            wrapXYZ["Field Z"] = amountaz
        }
        val somX: String? = wrapXYZ["Field X"] as String?
        val somY: String? = wrapXYZ["Field Y"] as String?
        val somZ: String? = wrapXYZ["Field Z"] as String?
        if (somX != null) {
            if (somY != null) {
                if (somZ != null) {
                    val somXYZ = (somX.toDouble() + somY.toDouble() + somZ.toDouble())
                    val somFactorize = "%.4f".format(somXYZ)
                    amountXYZ.text = somFactorize
                } else {
                    Toast.makeText(this, "Use 0 for replacement", Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this, "Use 0 for replacement", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                this, "Use 0 for replacement", Toast.LENGTH_LONG).show()
        //}
        //for (entertedInFields in wrapXYZ) {
        //    Log.i(TAG, "entry--> $entertedInFields")
        }
            return wrapXYZ
    }
    private fun wrapToFbase(nameF: TextView, valueF: TextView, inlayF: TextView, aXYZ: TextView): Map<String, Any> {
        val namea = nameF.text.toString();
        val valuea = valueF.text.toString()
        val inlay = inlayF.text.toString()
        val amountaxyz = aXYZ.text.toString()
        val wrapToFBase = mutableMapOf<String, Any>()
        if (namea.isNotEmpty()) {
            wrapToFBase["namez"] = namea
        }
        if (valuea.isNotEmpty()) {
            wrapToFBase["valuez"] = valuea
        }
        if (amountaxyz.isNotEmpty()) {
            wrapToFBase["partysz"] = amountaxyz
        }
        if (inlay.isNotEmpty()) {
            wrapToFBase["inlay"] = inlay
        }
        for (entertedInFields in wrapToFBase) {
            Log.i(TAG, "entry--> $entertedInFields")
        }
            return wrapToFBase
    }
    private fun calculateAmA(editText: EditText, textView: TextView, toost1: String)  {
        val aMa = editText.text.toString()
        if (aMa.isNotEmpty()) {
            clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataOne = ClipData.newPlainText("key", aMa)
            clipboardManager.setPrimaryClip(clipDataOne)
            val clipDataSec: ClipData = clipboardManager.primaryClip!!
            val itemOne: ClipData.Item = clipDataSec.getItemAt(0)
            textView.text = itemOne.text.toString()
        } else {
            Toast.makeText(this, "$toost1 empty", Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun switchData(number: Int, dataChange: Map<String, Any>) = CoroutineScope(IO).launch {
        val dataFundZQuery = datafundsCollectionRef
            .whereEqualTo("numberz", number)
            .get()
            .await()
            Log.i(TAG, "$dataFundZQuery")
        if (dataFundZQuery.documents.isNotEmpty()) {
            for (dataBlock in dataFundZQuery) {
                try {
                    datafundsCollectionRef.document(dataBlock.id).set(
                        dataChange,
                        SetOptions.merge())
                        .await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RefreshDataActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@RefreshDataActivity, "S.W.W !",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun caluTotalValueFundA(Y: TextView, Z: TextView, YZ: TextView): Map<String, Any> {
        val value = Y.text.toString()
        val totalpartys = Z.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (value.isNotEmpty()) {
            caluMap["Y"] = value
        }
        if (totalpartys.isNotEmpty()) {
            caluMap["Z"] = totalpartys
        }
        val keyY: String? = caluMap["Y"] as String?
        val keyZ: String? = caluMap["Z"] as String?

        if (keyY != null) {
            if (keyZ != null) {
                val kap = (keyY.toDouble() * keyZ.toDouble())
                val kapFactorize = "%.2f".format(kap)
                YZ.text = kapFactorize
            } else {
                Toast.makeText(this, "Total value NOT Calculated", Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this, "Total value NOT Calculated", Toast.LENGTH_LONG).show()
        }
            return caluMap
        }
    private fun caluTest123() = CoroutineScope(IO).launch {
        try {
            val datafundQuery = datafundsCollectionRef.get().await()
            val sb = StringBuilder()
            for (datafund in datafundQuery.documents) {
                val datafunix = datafund.toObject<DataFund>()
                sb.append("$datafunix\n")
                //Log.i(TAG, "$datafunix\n")
            }
            withContext(Dispatchers.Main) {
             tvtest123.text = sb.toString()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main)  {
                Toast.makeText(this@RefreshDataActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun oldDataDystrybutor(number: Int,
                                   DraadA: String, TvA: Any,
                                   DraadB: String, TvB: Any,
                                   DraadC: String, TvC: Any,
                                   DraadD: String, TvD: Any,
                                   DraadE: String, TvE: Any,
                                   DraadF: String, TvF: Any,
                                   DraadG: String, TvG: Any,
                                   DraadH: String, TvH: Any,
                                   DraadI: String, TvI: Any,
                                   DraadJ: String, TvJ: Any)
    = CoroutineScope(IO).launch {
        val dataQuer1 = datafundsCollectionRef
            .whereEqualTo("numberz", number)
            .get()
            .await()
        if (dataQuer1.documents.isNotEmpty()) {
            for (data in dataQuer1) {
                val dataBlok1 = datafundsCollectionRef.document(data.id)
                val inlayValue = findViewById<TextView>(TvA as Int)
                val partyValue = findViewById<TextView>(TvB as Int)
                val partyFund = findViewById<TextView>(TvC as Int)
                val totalFund = findViewById<TextView>(TvD as Int)
                val multiGrow = findViewById<TextView>(TvE as Int)
                val totalGrow = findViewById<TextView>(TvF as Int)
                val multiPerc = findViewById<TextView>(TvG as Int)
                val totalPerc = findViewById<TextView>(TvH as Int)
                val partyGrow = findViewById<TextView>(TvI as Int)
                val partyPerc = findViewById<TextView>(TvJ as Int)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                inlayValue.text = document.getString(DraadA)
                                partyValue.text = document.getString(DraadB)
                                partyFund.text = document.getString(DraadC)
                                totalFund.text = document.getString(DraadD)
                                multiGrow.text = document.getString(DraadE)
                                totalGrow.text = document.getString(DraadF)
                                multiPerc.text = document.getString(DraadG)
                                totalPerc.text = document.getString(DraadH)
                                partyGrow.text = document.getString(DraadI)
                                partyPerc.text = document.getString(DraadJ)
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
            }
        }
    }
}