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
import androidx.recyclerview.widget.RecyclerView
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

private const val TAG = "ARefreshDataActivity"
open class ARefreshDataActivity : AppCompatActivity() {
    //A//
    lateinit var tvfundnamea: TextView
    lateinit var etfundnamechangea: EditText
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
    //B//
    lateinit var tvfundnameb: TextView
    lateinit var etfundnamechangeb: EditText
    lateinit var tvvalueb: TextView
    lateinit var etvalueb: EditText
    lateinit var tvinlayb: TextView
    lateinit var etinlayb: EditText
    lateinit var tvamountbxyz: TextView
    lateinit var tvamountbx: TextView
    lateinit var tvamountby: TextView
    lateinit var tvamountbz: TextView
    lateinit var etamountbx: EditText
    lateinit var etamountby: EditText
    lateinit var etamountbz: EditText
    //A//
    lateinit var tvastotalfunda: TextView
    lateinit var tvatmultigrowa: TextView
    lateinit var tvautotalgrowa: TextView
    lateinit var tvavmultiperca: TextView
    lateinit var tvawtotalperca: TextView
    lateinit var tvadpartygrowa: TextView
    lateinit var tvaepartyperca: TextView
    lateinit var tvreservea0: TextView
    //B//
    lateinit var tvastotalfundb: TextView
    lateinit var tvatmultigrowb: TextView
    lateinit var tvautotalgrowb: TextView
    lateinit var tvavmultipercb: TextView
    lateinit var tvawtotalpercb: TextView
    lateinit var tvadpartygrowb: TextView
    lateinit var tvaepartypercb: TextView
    lateinit var tvreserveb0: TextView
    //A//
    lateinit var tvinlayaold: TextView
    lateinit var tvtotalfundaold: TextView
    lateinit var tvamountaxyzold: TextView
    //B//
    lateinit var tvinlaybold: TextView
    lateinit var tvtotalfundbold: TextView
    lateinit var tvamountbxyzold: TextView

    open lateinit var clipboardManager: ClipboardManager
    private lateinit var firestoreDb: FirebaseFirestore
    private val datafundsCollectionRef = Firebase.firestore.collection("datafunds")
    //A//
    private lateinit var datafundz: MutableList<DataFund>
    private lateinit var adapterDataFundz: ZDataFundAdapter
    //B//
    private lateinit var datafundy: MutableList<DataFund>
    private lateinit var adapterDataFundy: YDataFundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_data)
        //A//
        dataToRecyclerViewZ(rvdatafundsa, 1 )
        //B//
        dataToRecyclerViewY(rvdatafundsb, 2)
        //A//
        oldDataDystrybutor(1, "inlay", R.id.tvinlayaold, "valuez", R.id.tvvalueaold,
            "partysz", R.id.tvamountaxyzold, "totalfundz", R.id.tvtotalfundaold,
            "multigrowz", R.id.tvmultigrowaold, "totalgrowz", R.id.tvtotalgrowaold,
            "multipercz", R.id.tvmultipercaold, "totalpercz", R.id.tvtotalpercaold,
            "partygrowz", R.id.tvpartygrowaold, "partypercz", R.id.tvpartypercaold)
        //B//
        oldDataDystrybutor(2, "inlay", R.id.tvinlaybold, "valuez", R.id.tvvaluebold,
            "partysz", R.id.tvamountbxyzold, "totalfundz", R.id.tvtotalfundbold,
            "multigrowz", R.id.tvmultigrowbold, "totalgrowz", R.id.tvtotalgrowbold,
            "multipercz", R.id.tvmultipercbold, "totalpercz", R.id.tvtotalpercbold,
            "partygrowz", R.id.tvpartygrowbold, "partypercz", R.id.tvpartypercbold)
        //A//
        tvfundnamea = findViewById(R.id.tvfundnamea)
        etfundnamechangea = findViewById(R.id.etfundnamechangea)
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
        //B//
        tvfundnameb = findViewById(R.id.tvfundnameb)
        etfundnamechangeb = findViewById(R.id.etfundnamechangeb)
        tvvalueb = findViewById(R.id.tvvalueb)
        etvalueb = findViewById(R.id.etvalueb)
        tvinlayb = findViewById(R.id.tvinlayb)
        etinlayb = findViewById(R.id.etinlayb)
        tvamountbxyz = findViewById(R.id.tvamountbxyz)
        etamountbx = findViewById(R.id.etamountbx)
        etamountby = findViewById(R.id.etamountby)
        etamountbz = findViewById(R.id.etamountbz)
        tvamountbx = findViewById(R.id.tvamountbx)
        tvamountby = findViewById(R.id.tvamountby)
        tvamountbz = findViewById(R.id.tvamountbz)
        //A//
        tvinlayaold = findViewById(R.id.tvinlayaold)
        tvtotalfundaold = findViewById(R.id.tvtotalfundaold)
        tvamountaxyzold = findViewById(R.id.tvamountaxyzold)
        //B//
        tvinlaybold = findViewById(R.id.tvinlaybold)
        tvtotalfundbold = findViewById(R.id.tvtotalfundbold)
        tvamountbxyzold = findViewById(R.id.tvamountbxyzold)
        //A//
        tvastotalfunda = findViewById(R.id.tvastotalfunda)
        tvatmultigrowa = findViewById(R.id.tvatmultigrowa)
        tvautotalgrowa = findViewById(R.id.tvautotalgrowa)
        tvavmultiperca = findViewById(R.id.tvavmultiperca)
        tvawtotalperca = findViewById(R.id.tvawtotalperca)
        tvadpartygrowa = findViewById(R.id.tvadpartygrowa)
        tvaepartyperca = findViewById(R.id.tvaepartyperca)
        tvreservea0 = findViewById(R.id.tvreservea0)
        //B//
        tvastotalfundb = findViewById(R.id.tvastotalfundb)
        tvatmultigrowb = findViewById(R.id.tvatmultigrowb)
        tvautotalgrowb = findViewById(R.id.tvautotalgrowb)
        tvavmultipercb = findViewById(R.id.tvavmultipercb)
        tvawtotalpercb = findViewById(R.id.tvawtotalpercb)
        tvadpartygrowb = findViewById(R.id.tvadpartygrowb)
        tvaepartypercb = findViewById(R.id.tvaepartypercb)
        tvreserveb0 = findViewById(R.id.tvreserveb0)
        //A//
        swcalculatinga.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                swcalculatinga.text = resources.getString(R.string.clicker_Calculate)
                swcalculatinga.setTextColor(resources.getColor(R.color.oldtext))

                editToText(etfundnamechangea, tvfundnamea)
                editToText(etvaluea, tvvaluea)
                editToText(etinlaya, tvinlaya)
                editToTextWithToast(etamountax, tvamountax)
                editToTextWithToast(etamountay, tvamountay)
                editToTextWithToast(etamountaz, tvamountaz)

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
            //A//
            btnfirebasea.setOnClickListener {
                switchData(1, wrapToFbase(tvfundnamea, tvvaluea, tvinlaya, tvamountaxyz,
                    tvastotalfunda, tvatmultigrowa, tvautotalgrowa, tvavmultiperca, tvawtotalperca,
                    tvaepartyperca, tvadpartygrowa))
            }
        }
        //B//
        swcalculatingb.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                swcalculatingb.text = resources.getString(R.string.clicker_Calculate)
                swcalculatingb.setTextColor(resources.getColor(R.color.oldtext))

                editToText(etfundnamechangeb, tvfundnameb)
                editToText(etvalueb, tvvalueb)
                editToText(etinlayb, tvinlayb)
                editToTextWithToast(etamountbx, tvamountbx)
                editToTextWithToast(etamountby, tvamountby)
                editToTextWithToast(etamountbz, tvamountbz)

                sumXYZ(tvamountbx, tvamountby, tvamountbz, tvamountbxyz)
                wrapToFbase(tvfundnameb, tvvalueb, tvamountbxyz, tvinlayb, tvastotalfundb,
                    tvatmultigrowb, tvautotalgrowb, tvavmultipercb, tvawtotalpercb, tvaepartypercb,
                    tvadpartygrowb)

                caluMultiply(tvvalueb, tvamountbxyz, tvastotalfundb)
                caluSubtract(tvastotalfundb, tvtotalfundbold, tvatmultigrowb, "%.2f")
                caluSubtract(tvastotalfundb, tvinlaybold, tvautotalgrowb, "%.2f")
                caluPercentage(tvatmultigrowb, tvtotalfundbold, tvavmultipercb)
                caluPercentage(tvautotalgrowb, tvinlaybold, tvawtotalpercb)
                caluSubtract(tvamountbxyz, tvamountbxyzold, tvadpartygrowb, "%.4f")
                caluPercentage(tvadpartygrowb, tvamountbxyz, tvaepartypercb)

                btnfirebaseb.setTextColor(resources.getColor(R.color.oldtext))
            } else {
                swcalculatingb.text = resources.getString(R.string.clicker_De_Calculate)
                swcalculatingb.setTextColor(resources.getColor(R.color.transparent_color))
                tvfundnameb.text = ""
                tvvalueb.text = ""
                tvinlayb.text = ""
                tvamountbxyz.text = ""
                tvamountbx.text = ""
                tvamountby.text = ""
                tvamountbz.text = ""

                tvastotalfundb.text = ""
                tvatmultigrowb.text = ""
                tvautotalgrowb.text = ""
                tvavmultipercb.text = ""
                tvawtotalpercb.text = ""
                tvadpartygrowb.text = ""
                tvaepartypercb.text = ""

                btnfirebaseb.setTextColor(resources.getColor(R.color.transparent_color))
                Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
            }
            //B//
            btnfirebaseb.setOnClickListener {
                switchData(2, wrapToFbase(tvfundnameb, tvvalueb, tvinlayb, tvamountbxyz,
                    tvastotalfundb, tvatmultigrowb, tvautotalgrowb, tvavmultipercb, tvawtotalpercb,
                    tvaepartypercb, tvadpartygrowb))
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
    private fun dataToRecyclerViewZ(rvxXx: RecyclerView, fundno: Int) {
        datafundz = mutableListOf()
        adapterDataFundz = ZDataFundAdapter(this, datafundz)
        rvxXx.adapter = adapterDataFundz
        rvxXx.layoutManager = LinearLayoutManager(this)

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
            adapterDataFundz.notifyDataSetChanged()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun dataToRecyclerViewY(rvxXx: RecyclerView, fundno: Int) {
        datafundy = mutableListOf()
        adapterDataFundy = YDataFundAdapter(this, datafundy)
        rvxXx.adapter = adapterDataFundy
        rvxXx.layoutManager = LinearLayoutManager(this)

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
            datafundy.clear()
            datafundy.addAll(dataFundsList)
            adapterDataFundy.notifyDataSetChanged()
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
    private fun editToText(editinput: EditText, textinput: TextView) {
        val aMa = editinput.text.toString()
        if (aMa.isNotEmpty()) {
                clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clipDataOne = ClipData.newPlainText("key", aMa)
                clipboardManager.setPrimaryClip(clipDataOne)
                val clipDataSec: ClipData = clipboardManager.primaryClip!!
                val itemOne: ClipData.Item = clipDataSec.getItemAt(0)
                textinput.text = itemOne.text.toString()
        } else {
            Log.i(TAG, "#\n$editinput= (INVALID)*\n$textinput = (INVALID)*") }
    }
    private fun editToTextWithToast(editinput: EditText, textinput: TextView) {
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
                Toast.makeText(this@ARefreshDataActivity, "Amount partys can't be empty",
                    Toast.LENGTH_SHORT).show() }
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
                            this@ARefreshDataActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@ARefreshDataActivity, "S.W.W !",
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
                                    this@ARefreshDataActivity,
                                    "Error: No Data", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this@ARefreshDataActivity,
                                "Error: Fetching failure", Toast.LENGTH_SHORT
                            ).show()
                        }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ARefreshDataActivity, e.message, Toast.LENGTH_LONG
                        ).show()
                        Log.i(TAG, "-1-ERROR Fetching DATA")
                    }
                }
            }
        }
    }
}