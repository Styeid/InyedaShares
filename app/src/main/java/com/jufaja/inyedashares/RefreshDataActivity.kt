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
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.activity_refresh_data.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await

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
    lateinit var tvreservea0: TextView
    lateinit var tvadpartygrowa: TextView
    lateinit var tvaepartyperca: TextView

    lateinit var tvinlayaold: TextView
    lateinit var tvtotalfundaold: TextView
    lateinit var tvamountaxyzold: TextView

    lateinit var clipboardManager: ClipboardManager
    private lateinit var firestoreDb: FirebaseFirestore
    private val datafundsCollectionRef = Firebase.firestore.collection("datafunds")
    private lateinit var datafundz: MutableList<DataFund>
    private lateinit var adapterDataFund: DataFundsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_data)
        dataToRecyclerView(1)
        oldDataDystrybutor(1, "inlay", R.id.tvinlayaold, "valuez", R.id.tvvalueaold,
            "partysz", R.id.tvamountaxyzold, "totalfundz", R.id.tvtotalfundaold,
            "multigrowz", R.id.tvmultigrowaold, "totalgrowz", R.id.tvtotalgrowaold,
            "multipercz", R.id.tvmultipercaold, "totalpercz", R.id.tvtotalpercaold,
            "partygrowz", R.id.tvpartygrowaold, "partypercz", R.id.tvpartypercaold)

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

        tvinlayaold = findViewById(R.id.tvinlayaold)
        tvtotalfundaold = findViewById(R.id.tvtotalfundaold)
        tvamountaxyzold = findViewById(R.id.tvamountaxyzold)

        tvastotalfunda = findViewById(R.id.tvastotalfunda)
        tvatmultigrowa = findViewById(R.id.tvatmultigrowa)
        tvautotalgrowa = findViewById(R.id.tvautotalgrowa)
        tvavmultiperca = findViewById(R.id.tvavmultiperca)
        tvawtotalperca = findViewById(R.id.tvawtotalperca)
        tvreservea0 = findViewById(R.id.tvreservea0)
        tvadpartygrowa = findViewById(R.id.tvadpartygrowa)
        tvaepartyperca = findViewById(R.id.tvaepartyperca)

        swcalculatinga.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                swcalculatinga.text = resources.getString(R.string.clicker_Calculate)
                swcalculatinga.setTextColor(resources.getColor(R.color.oldtext))

                editToText(etfundnamechange, tvfundnamea)
                editToText(etvaluea, tvvaluea)
                editToText(etinlaya, tvinlaya)
                editToTextWithToast(etamountax, tvamountax, "Amount partys can't be")
                editToTextWithToast(etamountay, tvamountay, "Amount partys can't be")
                editToTextWithToast(etamountaz, tvamountaz, "Amount partys can't be")

                sumXYZ(tvamountax, tvamountay, tvamountaz, tvamountaxyz)
                wrapToFbase(tvfundnamea, tvvaluea, tvamountaxyz, tvinlaya, tvastotalfunda,
                    tvatmultigrowa, tvautotalgrowa, tvavmultiperca, tvawtotalperca, tvaepartyperca,
                    tvadpartygrowa)

                caluMultiply(tvvaluea, tvamountaxyz, tvastotalfunda)
                caluSubtract(tvastotalfunda, tvtotalfundaold, tvatmultigrowa, "%.2f")
                caluSubtract(tvastotalfunda, tvinlayaold, tvautotalgrowa, "%.2f")
                caluPercentage(tvatmultigrowa, tvtotalfundaold, tvavmultiperca)
                caluPercentage(tvautotalgrowa, tvinlayaold, tvawtotalperca)
                caluSubtract(tvamountaxyz, tvamountaxyzold, tvadpartygrowa, "%.4f")
                caluPercentage(tvadpartygrowa, tvamountaxyz, tvaepartyperca)

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
                tvatmultigrowa.text = ""
                tvautotalgrowa.text = ""
                tvavmultiperca.text = ""
                tvawtotalperca.text = ""
                tvadpartygrowa.text = ""
                tvaepartyperca.text = ""

                btnfirebasea.setTextColor(resources.getColor(R.color.transparent_color))
                Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
            }
            btnfirebasea.setOnClickListener {
                switchData(1, wrapToFbase(tvfundnamea, tvvaluea, tvinlaya, tvamountaxyz,
                    tvastotalfunda, tvatmultigrowa, tvautotalgrowa, tvavmultiperca, tvawtotalperca,
                    tvaepartyperca, tvadpartygrowa))
                //caluPercentage(tvadpartygrowa, tvamountaxyz, tvaepartyperca)
            }
        }
        swbeta01.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                swbeta01.text = resources.getString(R.string.clicker_beta)
                swbeta01.setTextColor(resources.getColor(R.color.accent_red))

                //caluPercentage(tvadpartygrowa, tvamountaxyz, tvaepartyperca)

                Toast.makeText(this, "Beta Action ON", Toast.LENGTH_SHORT).show()
            } else {
                swbeta01.text = resources.getString(R.string.clicker_De_beta)
                swbeta01.setTextColor(resources.getColor(R.color.transparent_color))



                Toast.makeText(this, "Beta Action OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun dataToRecyclerView(fundno: Int) {
        datafundz = mutableListOf()
        adapterDataFund = DataFundsAdapter(this, datafundz)
        rvdatafundsa.adapter = adapterDataFund
        rvdatafundsa.layoutManager = LinearLayoutManager(this)

        firestoreDb = FirebaseFirestore.getInstance()
        val datafundsReference = firestoreDb
            .collection("datafunds")
            .whereEqualTo("numberz", fundno)
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
    private fun wrapToFbase(nameP: TextView, valueP: TextView, inlayP: TextView, amountP: TextView,
                            totalfundPO: TextView, multigrowPO: TextView, totalgrowPO: TextView,
                            multipercPO: TextView, totalpercPO: TextView, partypercPO: TextView,
                            partygrowPO: TextView): Map<String, Any> {
        val namea = nameP.text.toString();
        val valuea = valueP.text.toString()
        val inlay = inlayP.text.toString()
        val amountaxyz = amountP.text.toString()
        val totalfund = totalfundPO.text.toString()
        val multigrow = multigrowPO.text.toString();
        val totalgrow = totalgrowPO.text.toString()
        val multiperc = multipercPO.text.toString()
        val totalperc = totalpercPO.text.toString()
        val partyperc = partypercPO.text.toString()
        val partygrow = partygrowPO.text.toString()
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
        if (totalfund.isNotEmpty()) {
            wrapToFBase["totalfundz"] = totalfund
        }
        if (multigrow.isNotEmpty()) {
            wrapToFBase["multigrowz"] = multigrow
        }
        if (totalgrow.isNotEmpty()) {
            wrapToFBase["totalgrowz"] = totalgrow
        }
        if (multiperc.isNotEmpty()) {
            wrapToFBase["multipercz"] = multiperc
        }
        if (totalperc.isNotEmpty()) {
            wrapToFBase["totalpercz"] = totalperc
        }
        if (partyperc.isNotEmpty()) {
            wrapToFBase["partypercz"] = partyperc
        }
        if (partygrow.isNotEmpty()) {
            wrapToFBase["partygrowz"] = partyperc
        }
        //for (entertedInFields in wrapToFBase) {
        //    Log.i(TAG, "entry--> $entertedInFields")
        //}
            return wrapToFBase
    }
    private fun editToText(editinput: EditText, textinput: TextView)  {
        val aMa = editinput.text.toString()
        if (aMa.isNotEmpty()) {
            clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataOne = ClipData.newPlainText("key", aMa)
            clipboardManager.setPrimaryClip(clipDataOne)
            val clipDataSec: ClipData = clipboardManager.primaryClip!!
            val itemOne: ClipData.Item = clipDataSec.getItemAt(0)
            textinput.text = itemOne.text.toString()
        } else {
            Log.i(TAG, "#\n$editinput= (INVALID)*\n$textinput = (INVALID)*")
        }
    }
    private fun editToTextWithToast(editinput: EditText, textinput: TextView, toost1: String)  {
        val aMa = editinput.text.toString()
        if (aMa.isNotEmpty()) {
            clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataOne = ClipData.newPlainText("key", aMa)
            clipboardManager.setPrimaryClip(clipDataOne)
            val clipDataSec: ClipData = clipboardManager.primaryClip!!
            val itemOne: ClipData.Item = clipDataSec.getItemAt(0)
            textinput.text = itemOne.text.toString()
        } else {
            Log.i(TAG, "#\n$editinput= (INVALID)*\n$textinput = (INVALID)*")
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
    private fun caluMultiply(Y: TextView, Z: TextView, YZ: TextView): Map<String, Any> {
        val dataY = Y.text.toString()
        val dataZ = Z.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (dataY.isNotEmpty()) {
            caluMap["Y"] = dataY
        }
        if (dataZ.isNotEmpty()) {
            caluMap["Z"] = dataZ
        }
        val keyY: String? = caluMap["Y"] as String?
        val keyZ: String? = caluMap["Z"] as String?

        if (keyY != null) {
            if (keyZ != null) {
                val kap = (keyY.toDouble() * keyZ.toDouble())
                val kapFactorize = "%.2f".format(kap)
                YZ.text = kapFactorize
            } else {
                Log.i (TAG, "#\n$Z MultiplyZ= $dataZ(INVALID)")
            }
        } else {
            Log.i (TAG, "#\n$Y MultiplyY= $dataY(INVALID)")
        }
            return caluMap
        }
    private fun caluSubtract(Y: TextView, Z: TextView, YZ: TextView, factor: String): Map<String, Any> {
        val dataY = Y.text.toString()
        val dataZ = Z.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (dataY.isNotEmpty()) {
            caluMap["Y"] = dataY
        }
        if (dataZ.isNotEmpty()) {
            caluMap["Z"] = dataZ
        }
        val keyY: String? = caluMap["Y"] as String?
        val keyZ: String? = caluMap["Z"] as String?

        if (keyY != null) {
            if (keyZ != null) {
                val kap = (keyY.toDouble() - keyZ.toDouble())
                val kapFactorize = factor.format(kap)
                YZ.text = kapFactorize
            } else {
                Log.i (TAG, "#\n$Z SubtractZ= $dataZ(INVALID)")
            }
        } else {
            Log.i (TAG, "#\n$Y SubtractY= $dataY(INVALID)")
        }
        return caluMap
    }
    private fun caluPercentage(Y: TextView, Z: TextView, YZ: TextView): Map<String, Any> {
        val dataY = Y.text.toString()
        val dataZ = Z.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (dataY.isNotEmpty()) {
            caluMap["Y"] = dataY
        }
        if (dataZ.isNotEmpty()) {
            caluMap["Z"] = dataZ
        }
        val keyY: String? = caluMap["Y"] as String?
        val keyZ: String? = caluMap["Z"] as String?

        if (keyY != null) {
            if (keyZ != null) {
                val kap = (keyY.toDouble() / keyZ.toDouble() * 100)
                val kapFactorize = "%.2f".format(kap)
                YZ.text = kapFactorize
            } else {
                Log.i (TAG, "#\n$Z PercentageZ= $dataZ(INVALID)")
            }
        } else {
            Log.i (TAG, "#\n$Y PercentageY= $dataY(INVALID)")
        }
        return caluMap
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
                                Toast.makeText(
                                    this@RefreshDataActivity,
                                    "Error: No Data", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this@RefreshDataActivity,
                                "Error: Fetching failure", Toast.LENGTH_SHORT
                            ).show()
                        }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RefreshDataActivity, e.message, Toast.LENGTH_LONG
                        ).show()
                        Log.i(TAG, "-1-ERROR Fetching DATA")
                    }
                }
            }
        }
    }
}