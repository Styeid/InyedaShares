package com.jufaja.inyedashares

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jufaja.inyedashares.models.DataFund
import com.jufaja.inyedashares.models.User
import kotlinx.android.synthetic.main.activity_refresh_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

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
    //C//
    lateinit var tvfundnamec: TextView
    lateinit var etfundnamechangec: EditText
    lateinit var tvvaluec: TextView
    lateinit var etvaluec: EditText
    lateinit var tvinlayc: TextView
    lateinit var etinlayc: EditText
    lateinit var tvamountcxyz: TextView
    lateinit var tvamountcx: TextView
    lateinit var tvamountcy: TextView
    lateinit var tvamountcz: TextView
    lateinit var etamountcx: EditText
    lateinit var etamountcy: EditText
    lateinit var etamountcz: EditText
    //D//
    lateinit var tvfundnamed: TextView
    lateinit var etfundnamechanged: EditText
    lateinit var tvvalued: TextView
    lateinit var etvalued: EditText
    lateinit var tvinlayd: TextView
    lateinit var etinlayd: EditText
    lateinit var tvamountdxyz: TextView
    lateinit var tvamountdx: TextView
    lateinit var tvamountdy: TextView
    lateinit var tvamountdz: TextView
    lateinit var etamountdx: EditText
    lateinit var etamountdy: EditText
    lateinit var etamountdz: EditText
    //A//
    lateinit var tvastotalfunda: TextView
    lateinit var tvatmultigrowa: TextView
    lateinit var tvautotalgrowa: TextView
    lateinit var tvavmultiperca: TextView
    lateinit var tvawtotalperca: TextView
    lateinit var tvadpartygrowa: TextView
    lateinit var tvaepartyperca: TextView
    lateinit var tvfundnameaold: TextView
    //B//
    lateinit var tvastotalfundb: TextView
    lateinit var tvatmultigrowb: TextView
    lateinit var tvautotalgrowb: TextView
    lateinit var tvavmultipercb: TextView
    lateinit var tvawtotalpercb: TextView
    lateinit var tvadpartygrowb: TextView
    lateinit var tvaepartypercb: TextView
    lateinit var tvfundnamebold: TextView
    //C//
    lateinit var tvastotalfundc: TextView
    lateinit var tvatmultigrowc: TextView
    lateinit var tvautotalgrowc: TextView
    lateinit var tvavmultipercc: TextView
    lateinit var tvawtotalpercc: TextView
    lateinit var tvadpartygrowc: TextView
    lateinit var tvaepartypercc: TextView
    lateinit var tvfundnamecold: TextView
    //D//
    lateinit var tvastotalfundd: TextView
    lateinit var tvatmultigrowd: TextView
    lateinit var tvautotalgrowd: TextView
    lateinit var tvavmultipercd: TextView
    lateinit var tvawtotalpercd: TextView
    lateinit var tvadpartygrowd: TextView
    lateinit var tvaepartypercd: TextView
    lateinit var tvfundnamedold: TextView
    //Aold//
    lateinit var tvinlayaold: TextView
    lateinit var tvtotalfundaold: TextView
    lateinit var tvvalueaold: TextView
    lateinit var tvamountaxyzold: TextView
    lateinit var tvtotalgrowaold: TextView
    lateinit var tvmultipercaold: TextView
    lateinit var tvtotalpercaold: TextView
    lateinit var tvmultigrowaold: TextView
    lateinit var tvpartygrowaold: TextView
    lateinit var tvpartypercaold: TextView
    //Bold//
    lateinit var tvinlaybold: TextView
    lateinit var tvtotalfundbold: TextView
    lateinit var tvvaluebold: TextView
    lateinit var tvamountbxyzold: TextView
    lateinit var tvtotalgrowbold: TextView
    lateinit var tvmultipercbold: TextView
    lateinit var tvtotalpercbold: TextView
    lateinit var tvmultigrowbold: TextView
    lateinit var tvpartygrowbold: TextView
    lateinit var tvpartypercbold: TextView
    //Cold//
    lateinit var tvinlaycold: TextView
    lateinit var tvtotalfundcold: TextView
    lateinit var tvvaluecold: TextView
    lateinit var tvamountcxyzold: TextView
    lateinit var tvtotalgrowcold: TextView
    lateinit var tvmultiperccold: TextView
    lateinit var tvtotalperccold: TextView
    lateinit var tvmultigrowcold: TextView
    lateinit var tvpartygrowcold: TextView
    lateinit var tvpartyperccold: TextView
    //Dold//
    lateinit var tvinlaydold: TextView
    lateinit var tvtotalfunddold: TextView
    lateinit var tvvaluedold: TextView
    lateinit var tvamountdxyzold: TextView
    lateinit var tvtotalgrowdold: TextView
    lateinit var tvmultipercdold: TextView
    lateinit var tvtotalpercdold: TextView
    lateinit var tvmultigrowdold: TextView
    lateinit var tvpartygrowdold: TextView
    lateinit var tvpartypercdold: TextView
    ///Others?///
    lateinit var tvnamet: TextView
    lateinit var tvdatet: TextView
    lateinit var tvtotalvaluetold: TextView
    lateinit var tvtotalvaluet: TextView
    lateinit var tvprofitdayt: TextView
    lateinit var tvprofittotalt: TextView
    lateinit var tvpercentagedayt: TextView
    lateinit var tvpercentagetotalt: TextView
    lateinit var tvunitt7: TextView
    lateinit var tvunitt8: TextView

    open lateinit var clipboardManager: ClipboardManager
    private lateinit var firestoreDb: FirebaseFirestore
    private val datafundsCollectionRef = Firebase.firestore.collection("datafunds")
    private val datapostCollectionRef = Firebase.firestore.collection("datapost")
    var signedInUser: User? = null
    //A//
    private lateinit var datafundz: MutableList<DataFund>
    private lateinit var adapterDataFundz: ZDataFundAdapter
    //B//
    private lateinit var datafundy: MutableList<DataFund>
    private lateinit var adapterDataFundy: YDataFundAdapter
    //C//
    private lateinit var datafundx: MutableList<DataFund>
    private lateinit var adapterDataFundx: XDataFundAdapter
    //D//
    private lateinit var datafundw: MutableList<DataFund>
    private lateinit var adapterDataFundw: WDataFundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_data)
        //A//
        dataToRecyclerViewZ(rvdatafundsa, 1 )
        //B//
        dataToRecyclerViewY(rvdatafundsb, 2)
        //C//
        dataToRecyclerViewX(rvdatafundsc, 3)
        //D//
        dataToRecyclerViewW(rvdatafundsd, 4)
        //A//
        oldDataDystrybutor(1, R.id.tvinlayaold, R.id.tvvalueaold, R.id.tvamountaxyzold,
            R.id.tvtotalfundaold, R.id.tvmultigrowaold, R.id.tvtotalgrowaold, R.id.tvmultipercaold,
            R.id.tvtotalpercaold, R.id.tvpartygrowaold, R.id.tvpartypercaold, R.id.tvfundnameaold)
        //B//
        oldDataDystrybutor(2, R.id.tvinlaybold, R.id.tvvaluebold, R.id.tvamountbxyzold,
            R.id.tvtotalfundbold, R.id.tvmultigrowbold, R.id.tvtotalgrowbold, R.id.tvmultipercbold,
            R.id.tvtotalpercbold, R.id.tvpartygrowbold, R.id.tvpartypercbold, R.id.tvfundnamebold)
        //C//
        oldDataDystrybutor(3, R.id.tvinlaycold, R.id.tvvaluecold, R.id.tvamountcxyzold,
            R.id.tvtotalfundcold, R.id.tvmultigrowcold, R.id.tvtotalgrowcold, R.id.tvmultiperccold,
            R.id.tvtotalperccold, R.id.tvpartygrowcold, R.id.tvpartyperccold, R.id.tvfundnamecold)
        //D//
        oldDataDystrybutor(4, R.id.tvinlaydold, R.id.tvvaluedold, R.id.tvamountdxyzold,
            R.id.tvtotalfunddold, R.id.tvmultigrowdold, R.id.tvtotalgrowdold, R.id.tvmultipercdold,
            R.id.tvtotalpercdold, R.id.tvpartygrowdold, R.id.tvpartypercdold, R.id.tvfundnamedold)

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
        //C//
        tvfundnamec = findViewById(R.id.tvfundnamec)
        etfundnamechangec = findViewById(R.id.etfundnamechangec)
        tvvaluec = findViewById(R.id.tvvaluec)
        etvaluec = findViewById(R.id.etvaluec)
        tvinlayc = findViewById(R.id.tvinlayc)
        etinlayc = findViewById(R.id.etinlayc)
        tvamountcxyz = findViewById(R.id.tvamountcxyz)
        etamountcx = findViewById(R.id.etamountcx)
        etamountcy = findViewById(R.id.etamountcy)
        etamountcz = findViewById(R.id.etamountcz)
        tvamountcx = findViewById(R.id.tvamountcx)
        tvamountcy = findViewById(R.id.tvamountcy)
        tvamountcz = findViewById(R.id.tvamountcz)
        //D//
        tvfundnamed = findViewById(R.id.tvfundnamed)
        etfundnamechanged = findViewById(R.id.etfundnamechanged)
        tvvalued = findViewById(R.id.tvvalued)
        etvalued = findViewById(R.id.etvalued)
        tvinlayd = findViewById(R.id.tvinlayd)
        etinlayd = findViewById(R.id.etinlayd)
        tvamountdxyz = findViewById(R.id.tvamountdxyz)
        etamountdx = findViewById(R.id.etamountdx)
        etamountdy = findViewById(R.id.etamountdy)
        etamountdz = findViewById(R.id.etamountdz)
        tvamountdx = findViewById(R.id.tvamountdx)
        tvamountdy = findViewById(R.id.tvamountdy)
        tvamountdz = findViewById(R.id.tvamountdz)
        //Aold//
        tvinlayaold = findViewById(R.id.tvinlayaold)
        tvtotalfundaold = findViewById(R.id.tvtotalfundaold)
        tvvalueaold = findViewById(R.id.tvvalueaold)
        tvamountaxyzold = findViewById(R.id.tvamountaxyzold)
        tvtotalgrowaold = findViewById(R.id.tvtotalgrowaold)
        tvmultipercaold = findViewById(R.id.tvmultipercaold)
        tvtotalpercaold = findViewById(R.id.tvtotalpercaold)
        tvmultigrowaold = findViewById(R.id.tvmultigrowaold)
        tvpartygrowaold = findViewById(R.id.tvpartygrowaold)
        tvpartypercaold = findViewById(R.id.tvpartypercaold)
        //Bold//
        tvinlaybold = findViewById(R.id.tvinlaybold)
        tvtotalfundbold = findViewById(R.id.tvtotalfundbold)
        tvvaluebold = findViewById(R.id.tvvaluebold)
        tvamountbxyzold = findViewById(R.id.tvamountbxyzold)
        tvtotalgrowbold = findViewById(R.id.tvtotalgrowbold)
        tvmultipercbold = findViewById(R.id.tvmultipercbold)
        tvtotalpercbold = findViewById(R.id.tvtotalpercbold)
        tvmultigrowbold = findViewById(R.id.tvmultigrowbold)
        tvpartygrowbold = findViewById(R.id.tvpartygrowbold)
        tvpartypercbold = findViewById(R.id.tvpartypercbold)
        //Cold//
        tvinlaycold = findViewById(R.id.tvinlaycold)
        tvtotalfundcold = findViewById(R.id.tvtotalfundcold)
        tvvaluecold = findViewById(R.id.tvvaluecold)
        tvamountcxyzold = findViewById(R.id.tvamountcxyzold)
        tvtotalgrowcold = findViewById(R.id.tvtotalgrowcold)
        tvmultiperccold = findViewById(R.id.tvmultiperccold)
        tvtotalperccold = findViewById(R.id.tvtotalperccold)
        tvmultigrowcold = findViewById(R.id.tvmultigrowcold)
        tvpartygrowcold = findViewById(R.id.tvpartygrowcold)
        tvpartyperccold = findViewById(R.id.tvpartyperccold)
        //Dold//
        tvinlaydold = findViewById(R.id.tvinlaydold)
        tvtotalfunddold = findViewById(R.id.tvtotalfunddold)
        tvvaluedold = findViewById(R.id.tvvaluedold)
        tvamountdxyzold = findViewById(R.id.tvamountdxyzold)
        tvtotalgrowdold = findViewById(R.id.tvtotalgrowdold)
        tvmultipercdold = findViewById(R.id.tvmultipercdold)
        tvtotalpercdold = findViewById(R.id.tvtotalpercdold)
        tvmultigrowdold = findViewById(R.id.tvmultigrowdold)
        tvpartygrowdold = findViewById(R.id.tvpartygrowdold)
        tvpartypercdold = findViewById(R.id.tvpartypercdold)
        //A//
        tvastotalfunda = findViewById(R.id.tvastotalfunda)
        tvatmultigrowa = findViewById(R.id.tvatmultigrowa)
        tvautotalgrowa = findViewById(R.id.tvautotalgrowa)
        tvavmultiperca = findViewById(R.id.tvavmultiperca)
        tvawtotalperca = findViewById(R.id.tvawtotalperca)
        tvadpartygrowa = findViewById(R.id.tvadpartygrowa)
        tvaepartyperca = findViewById(R.id.tvaepartyperca)
        tvfundnameaold = findViewById(R.id.tvfundnameaold)
        //B//
        tvastotalfundb = findViewById(R.id.tvastotalfundb)
        tvatmultigrowb = findViewById(R.id.tvatmultigrowb)
        tvautotalgrowb = findViewById(R.id.tvautotalgrowb)
        tvavmultipercb = findViewById(R.id.tvavmultipercb)
        tvawtotalpercb = findViewById(R.id.tvawtotalpercb)
        tvadpartygrowb = findViewById(R.id.tvadpartygrowb)
        tvaepartypercb = findViewById(R.id.tvaepartypercb)
        tvfundnamebold = findViewById(R.id.tvfundnamebold)
        //C//
        tvastotalfundc = findViewById(R.id.tvastotalfundc)
        tvatmultigrowc = findViewById(R.id.tvatmultigrowc)
        tvautotalgrowc = findViewById(R.id.tvautotalgrowc)
        tvavmultipercc = findViewById(R.id.tvavmultipercc)
        tvawtotalpercc = findViewById(R.id.tvawtotalpercc)
        tvadpartygrowc = findViewById(R.id.tvadpartygrowc)
        tvaepartypercc = findViewById(R.id.tvaepartypercc)
        tvfundnamecold = findViewById(R.id.tvfundnamecold)
        //D//
        tvastotalfundd = findViewById(R.id.tvastotalfundd)
        tvatmultigrowd = findViewById(R.id.tvatmultigrowd)
        tvautotalgrowd = findViewById(R.id.tvautotalgrowd)
        tvavmultipercd = findViewById(R.id.tvavmultipercd)
        tvawtotalpercd = findViewById(R.id.tvawtotalpercd)
        tvadpartygrowd = findViewById(R.id.tvadpartygrowd)
        tvaepartypercd = findViewById(R.id.tvaepartypercd)
        tvfundnamedold = findViewById(R.id.tvfundnamedold)
        ///Others///
        tvnamet = findViewById(R.id.tvnamet)
        tvdatet = findViewById(R.id.tvdatet)
        tvtotalvaluetold = findViewById(R.id.tvtotalvaluetold)
        tvtotalvaluet = findViewById(R.id.tvtotalvaluet)
        tvprofitdayt = findViewById(R.id.tvprofitdayt)
        tvprofittotalt = findViewById(R.id.tvprofittotalt)
        tvpercentagedayt = findViewById(R.id.tvpercentagedayt)
        tvpercentagetotalt = findViewById(R.id.tvpercentagetotalt)
        tvunitt7 = findViewById(R.id.tvunitt7)
        tvunitt8 = findViewById(R.id.tvunitt8)

        btncalculatingt.isEnabled =false
        //A//
        btncalculatinga.setOnClickListener {
            btncalculatingt.isEnabled = true
            btnfirebasea.isEnabled = true
            btncalculatinga.setTextColor(resources.getColor(R.color.oldtext))

            editToText(etfundnamechangea, tvfundnamea)
            editToText(etvaluea, tvvaluea)
            editToText(etinlaya, tvinlaya)
            editToTextWithToast(etamountax, tvamountax)
            editToTextWithToast(etamountay, tvamountay)
            editToTextWithToast(etamountaz, tvamountaz)

            sumXYZ(tvamountax, tvamountay, tvamountaz, tvamountaxyz, tvamountaxyzold)
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

            wrapTotalBlok()
            btnfirebasea.setTextColor(resources.getColor(R.color.oldtext))

            //A//
            btnfirebasea.setOnClickListener {
                btnfirebasea.setTextColor(resources.getColor(R.color.transparent_dark))
                switchData(1, wrapToFbase(tvfundnamea, tvvaluea, tvinlaya, tvamountaxyz,
                    tvastotalfunda, tvatmultigrowa, tvautotalgrowa, tvavmultiperca, tvawtotalperca,
                    tvaepartyperca, tvadpartygrowa))
                btnfirebasea.isEnabled = false
            }
        }
        //B//
        btncalculatingb.setOnClickListener {
            btncalculatingt.isEnabled = true
            btnfirebaseb.isEnabled = true
            btncalculatingb.setTextColor(resources.getColor(R.color.oldtext))

            editToText(etfundnamechangeb, tvfundnameb)
            editToText(etvalueb, tvvalueb)
            editToText(etinlayb, tvinlayb)
            editToTextWithToast(etamountbx, tvamountbx)
            editToTextWithToast(etamountby, tvamountby)
            editToTextWithToast(etamountbz, tvamountbz)

            sumXYZ(tvamountbx, tvamountby, tvamountbz, tvamountbxyz, tvamountbxyzold)
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

            wrapTotalBlok()
            btnfirebaseb.setTextColor(resources.getColor(R.color.oldtext))

            //B//
            btnfirebaseb.setOnClickListener {
                btnfirebaseb.setTextColor(resources.getColor(R.color.transparent_dark))
                switchData(2, wrapToFbase(tvfundnameb, tvvalueb, tvinlayb, tvamountbxyz,
                    tvastotalfundb, tvatmultigrowb, tvautotalgrowb, tvavmultipercb, tvawtotalpercb,
                    tvaepartypercb, tvadpartygrowb))
                btnfirebaseb.isEnabled = false
            }
        }
        //C//
        btncalculatingc.setOnClickListener {
            btncalculatingt.isEnabled = true
            btnfirebasec.isEnabled = true
            btncalculatingc.setTextColor(resources.getColor(R.color.oldtext))

            editToText(etfundnamechangec, tvfundnamec)
            editToText(etvaluec, tvvaluec)
            editToText(etinlayc, tvinlayc)
            editToTextWithToast(etamountcx, tvamountcx)
            editToTextWithToast(etamountcy, tvamountcy)
            editToTextWithToast(etamountcz, tvamountcz)

            sumXYZ(tvamountcx, tvamountcy, tvamountcz, tvamountcxyz, tvamountcxyzold)
            wrapToFbase(tvfundnamec, tvvaluec, tvamountcxyz, tvinlayc, tvastotalfundc,
                tvatmultigrowc, tvautotalgrowc, tvavmultipercc, tvawtotalpercc, tvaepartypercc,
                tvadpartygrowc)

            caluMultiply(tvvaluec, tvamountcxyz, tvastotalfundc)
            caluSubtract(tvastotalfundc, tvtotalfundcold, tvatmultigrowc, "%.2f")
            caluSubtract(tvastotalfundc, tvinlaycold, tvautotalgrowc, "%.2f")
            caluPercentage(tvatmultigrowc, tvtotalfundcold, tvavmultipercc)
            caluPercentage(tvautotalgrowc, tvinlaycold, tvawtotalpercc)
            caluSubtract(tvamountcxyz, tvamountcxyzold, tvadpartygrowc, "%.4f")
            caluPercentage(tvadpartygrowc, tvamountcxyz, tvaepartypercc)

            wrapTotalBlok()
            btnfirebasec.setTextColor(resources.getColor(R.color.oldtext))

            //C//
            btnfirebasec.setOnClickListener {
                btnfirebasec.setTextColor(resources.getColor(R.color.transparent_dark))
                switchData(3, wrapToFbase(tvfundnamec, tvvaluec, tvinlayc, tvamountcxyz,
                    tvastotalfundc, tvatmultigrowc, tvautotalgrowc, tvavmultipercc, tvawtotalpercc,
                    tvaepartypercc, tvadpartygrowc))
                btnfirebasec.isEnabled = false
            }
        }
        //D//
        btncalculatingd.setOnClickListener {
            btncalculatingt.isEnabled = true
            btnfirebased.isEnabled = true
            btncalculatingd.setTextColor(resources.getColor(R.color.oldtext))

            editToText(etfundnamechanged, tvfundnamed)
            editToText(etvalued, tvvalued)
            editToText(etinlayd, tvinlayd)
            editToTextWithToast(etamountdx, tvamountdx)
            editToTextWithToast(etamountdy, tvamountdy)
            editToTextWithToast(etamountdz, tvamountdz)

            sumXYZ(tvamountdx, tvamountdy, tvamountdz, tvamountdxyz, tvamountdxyzold)
            wrapToFbase(tvfundnamed, tvvalued, tvamountdxyz, tvinlayd, tvastotalfundd,
                tvatmultigrowd, tvautotalgrowd, tvavmultipercd, tvawtotalpercd, tvaepartypercd,
                tvadpartygrowd)

            caluMultiply(tvvalued, tvamountdxyz, tvastotalfundd)
            caluSubtract(tvastotalfundd, tvtotalfunddold, tvatmultigrowd, "%.2f")
            caluSubtract(tvastotalfundd, tvinlaydold, tvautotalgrowd, "%.2f")
            caluPercentage(tvatmultigrowd, tvtotalfunddold, tvavmultipercd)
            caluPercentage(tvautotalgrowd, tvinlaydold, tvawtotalpercd)
            caluSubtract(tvamountdxyz, tvamountdxyzold, tvadpartygrowd, "%.4f")
            caluPercentage(tvadpartygrowd, tvamountdxyz, tvaepartypercd)

            wrapTotalBlok()
            btnfirebased.setTextColor(resources.getColor(R.color.oldtext))

            //D//
            btnfirebased.setOnClickListener {
                btnfirebased.setTextColor(resources.getColor(R.color.transparent_dark))
                switchData(4, wrapToFbase(tvfundnamed, tvvalued, tvinlayd, tvamountdxyz,
                    tvastotalfundd, tvatmultigrowd, tvautotalgrowd, tvavmultipercd, tvawtotalpercd,
                    tvaepartypercd, tvadpartygrowd))
                btnfirebased.isEnabled = false
            }
        }
        ///BTa///
        btncalculatingt.setOnClickListener {
            btncalculatingt.text = resources.getString(R.string.clicker_OnLock)
            btnfirebaset.setTextColor(resources.getColor(R.color.oldtext))

            keyValueDatapost()

            Toast.makeText(this, " unLOCKed", Toast.LENGTH_LONG).show()

            btnfirebaset.setOnClickListener {
                btnfirebaset.setTextColor(resources.getColor(R.color.transparent_dark))
                saveDatapost(keyValueDatapost())
                btnfirebaset.isEnabled = false
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
    @SuppressLint("NotifyDataSetChanged")
    private fun dataToRecyclerViewX(rvxXx: RecyclerView, fundno: Int) {
        datafundx = mutableListOf()
        adapterDataFundx = XDataFundAdapter(this, datafundx)
        rvxXx.adapter = adapterDataFundx
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
            datafundx.clear()
            datafundx.addAll(dataFundsList)
            adapterDataFundx.notifyDataSetChanged()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun dataToRecyclerViewW(rvxXx: RecyclerView, fundno: Int) {
        datafundw = mutableListOf()
        adapterDataFundw = WDataFundAdapter(this, datafundw)
        rvxXx.adapter = adapterDataFundw
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
            datafundw.clear()
            datafundw.addAll(dataFundsList)
            adapterDataFundw.notifyDataSetChanged()
        }
    }
    private fun sumXYZ(amountx: TextView, amounty: TextView, amountz: TextView, amountXYZO: TextView,
                       amountXYZI: TextView): Map<String, Any> {
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
                    val somXYZO = (somX.toDouble() + somY.toDouble() + somZ.toDouble())
                    val somFactorizeO = "%.4f".format(somXYZO)
                    amountXYZO.text = somFactorizeO
                } else {
                    amountXYZO.text = amountXYZI.text
                }
            } else {
                amountXYZO.text = amountXYZI.text
            }
        } else {
            amountXYZO.text = amountXYZI.text
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
        //}
        //for (entertedInFields in wrapToFBase) {
        //    Log.i(TAG, "entry--> $entertedInFields")
        }
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
                Toast.makeText(this@ARefreshDataActivity, "WARNING: When using amount fields," +
                        "\tremaining amount fields can't be empty.\n\t\t\t\tUse zero (0) as default for " +
                        "remaining amount fields.", Toast.LENGTH_SHORT).show() }
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
    private fun caluMultiply(X: TextView, Y: TextView, YZ: TextView): Map<String, Any> {
        val dataX = X.text.toString()
        val dataY = Y.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (dataX.isNotEmpty()) {
            caluMap["X"] = dataX
        }
        if (dataY.isNotEmpty()) {
            caluMap["Y"] = dataY
        }
        val keyX: String? = caluMap["X"] as String?
        val keyY: String? = caluMap["Y"] as String?
        if (keyX != null) {
            if (keyY != null) {
                val kapO = (keyX.toDouble() * keyY.toDouble())
                val kapFactorizeO = "%.2f".format(kapO)
                YZ.text = kapFactorizeO
            } else {
                Log.i (TAG, "#\n$Y MultiplyY= $dataY(INVALID)")
                }
        } else {
            Log.i (TAG, "#\n$Y MultiplyX= $dataX(INVALID)")
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
    private fun oldDataDystrybutor(number: Int, TvA: Any, TvB: Any, TvC: Any, TvD: Any, TvE: Any, TvF: Any,
                                   TvG: Any, TvH: Any, TvI: Any, TvJ: Any, TvK: Any) = CoroutineScope(IO).launch {
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
                val fundName = findViewById<TextView>(TvK as Int)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                inlayValue.text = document.getString("inlay")
                                partyValue.text = document.getString("valuez")
                                partyFund.text = document.getString("partysz")
                                totalFund.text = document.getString("totalfundz")
                                multiGrow.text = document.getString("multigrowz")
                                totalGrow.text = document.getString("totalgrowz")
                                multiPerc.text = document.getString("multipercz")
                                totalPerc.text = document.getString("totalpercz")
                                partyGrow.text = document.getString("partygrowz")
                                partyPerc.text = document.getString("partypercz")
                                fundName.text = document.getString("namez")
                            } else {
                                Toast.makeText(
                                    this@ARefreshDataActivity,
                                    "-1-Error: No Data", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this@ARefreshDataActivity,
                                "-2-Error: Fetching failure", Toast.LENGTH_SHORT
                            ).show()
                        }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ARefreshDataActivity, e.message, Toast.LENGTH_LONG
                        ).show()
                        Log.i(TAG, "-3-Error: Fetching DATA")
                    }
                }
            }
        }
    }
    ////TotalCalu_Function////
    private fun wrapTotalBlok() {
        setUserNameToTV()
        setCurrentDateToTV()
        caluProfitday()
        caluProfitTotalG()
        caluPrecentagedaily()
        caluPrecentageTotal()
        tvunitt7.setTextColor(resources.getColor(R.color.oldtext))
        tvunitt8.setTextColor(resources.getColor(R.color.oldtext))
        tvnamet.setTextColor(resources.getColor(R.color.oldtext))
        tvdatet.setTextColor(resources.getColor(R.color.oldtext))
    }
    private fun setUserNameToTV() {
        firestoreDb = FirebaseFirestore.getInstance()
        firestoreDb.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
            .get()
            .addOnSuccessListener { userSnapshot ->
                signedInUser = userSnapshot.toObject(User::class.java)
                val userNa = signedInUser?.username
                tvnamet.text = userNa
                Log.i(TAG, "Signed in user: $signedInUser")
            }
            .addOnFailureListener { exeption ->
                Log.i(TAG, "Failure fetching signed in user", exeption)
            }
    }
    @SuppressLint("SimpleDateFormat")
    private fun setCurrentDateToTV(): String {
        val currentDay: TextView = this.findViewById(R.id.tvdatet)
        val dateConventional = SimpleDateFormat("dd.MM.yyyy h:mm a")
        val currentDate: String = dateConventional.format(Date())
        currentDay.text = currentDate
        return currentDate
    }
    private fun caluTotalInlay(): Map<String, Any> {
        val dataX1 = tvinlayaold.text.toString()
        val dataX2 = tvinlaybold.text.toString()
        val dataX3 = tvinlaycold.text.toString()
        val dataX4 = tvtotalfunddold.text.toString()
        val caluMap2 = mutableMapOf<String, Any>()
        if (dataX1.isNotEmpty()) {
            caluMap2["W"] = dataX1
        }
        if (dataX2.isNotEmpty()) {
            caluMap2["X"] = dataX2
        }
        if (dataX3.isNotEmpty()) {
            caluMap2["Y"] = dataX3
        }
        if (dataX4.isNotEmpty()) {
            caluMap2["Z"] = dataX4
        }
        val keyW2: String? = caluMap2["W"] as String?
        val keyX2: String? = caluMap2["X"] as String?
        val keyY2: String? = caluMap2["Y"] as String?
        val keyZ2: String? = caluMap2["Z"] as String?

        if (keyW2 != null) {
            if (keyX2 != null) {
                if (keyY2 != null) {
                    if (keyZ2 != null) {
                        keyW2.toDouble() + keyX2.toDouble() + keyY2.toDouble() + keyZ2.toDouble()
                    } else {
                        Log.i (TAG, "#\n TotalInlayZ= $dataX4(INVALID)")
                    }
                } else {
                    Log.i (TAG, "#\n TotalInlayY= $dataX3(INVALID)")
                }

            } else {
                Log.i (TAG, "#\n TotalInlayX= $dataX2(INVALID)")
            }
        } else {
            Log.i (TAG, "#\n TotalInlayW= $dataX1(INVALID)")
        }
        return caluMap2
    }
    private fun caluTotalValueOld(): Map<String, Any> {
        val dataY1 = tvtotalfundaold.text.toString()
        val dataY2 = tvtotalfundbold.text.toString()
        val dataY3 = tvtotalfundcold.text.toString()
        val dataY4 = tvtotalfunddold.text.toString()
        val caluMap1 = mutableMapOf<String, Any>()
        if (dataY1.isNotEmpty()) {
            caluMap1["W"] = dataY1
        }
        if (dataY2.isNotEmpty()) {
            caluMap1["X"] = dataY2
        }
        if (dataY3.isNotEmpty()) {
            caluMap1["Y"] = dataY3
        }
        if (dataY4.isNotEmpty()) {
            caluMap1["Z"] = dataY4
        }
        val keyW1: String? = caluMap1["W"] as String?
        val keyX1: String? = caluMap1["X"] as String?
        val keyY1: String? = caluMap1["Y"] as String?
        val keyZ1: String? = caluMap1["Z"] as String?

        if (keyW1 != null) {
            if (keyX1 != null) {
                if (keyY1 != null) {
                    if (keyZ1 != null) {
                            val kap = (keyW1.toDouble() + keyX1.toDouble() +
                                    keyY1.toDouble() + keyZ1.toDouble())
                            val kapFactorize = "%.2f".format(kap)
                            tvtotalvaluetold.text = kapFactorize
                    } else {
                        Log.i(TAG, "#\n TotalValueOldZ= $dataY4(INVALID)")
                    }
                } else {
                    Log.i(TAG, "#\n TotalValueOldY= $dataY3(INVALID)")
                }

            } else {
                Log.i(TAG, "#\n TotalValueOldX= $dataY2(INVALID)")
            }
        } else {
            Log.i(TAG, "#\n TotalValueOldW= $dataY1(INVALID)")
        }
        return caluMap1
    }
    private fun caluTotalValueTopical(): Map<String, Any> {
        val dataY01 = tvtotalfundaold.text.toString()
        val dataY02 = tvtotalfundbold.text.toString()
        val dataY03 = tvtotalfundcold.text.toString()
        val dataY04 = tvtotalfunddold.text.toString()
        val dataY1 = tvastotalfunda.text.toString()
        val dataY2 = tvastotalfundb.text.toString()
        val dataY3 = tvastotalfundc.text.toString()
        val dataY4 = tvastotalfundd.text.toString()
        val caluMap = mutableMapOf<String, Any>()
        if (dataY1.isNotEmpty()) {
            caluMap["W"] = dataY1
        } else {
            caluMap["W"] = dataY01
        }
        if (dataY2.isNotEmpty()) {
            caluMap["X"] = dataY2
        } else {
            caluMap["X"] = dataY02
        }
        if (dataY3.isNotEmpty()) {
            caluMap["Y"] = dataY3
        } else {
            caluMap["Y"] = dataY03
        }
        if (dataY4.isNotEmpty()) {
            caluMap["Z"] = dataY4
        } else {
            caluMap["Z"] = dataY04
        }
        val keyW: String? = caluMap["W"] as String?
        val keyX: String? = caluMap["X"] as String?
        val keyY: String? = caluMap["Y"] as String?
        val keyZ: String? = caluMap["Z"] as String?

        if (keyW != null) {
            if (keyX != null) {
                if (keyY != null) {
                    if (keyZ != null) {
                        val kap = (keyW.toDouble() + keyX.toDouble() + keyY.toDouble() + keyZ.toDouble())
                        val kapFactorize = "%.2f".format(kap)
                        tvtotalvaluet.text = kapFactorize
                    } else {
                        Log.i (TAG, "#\n TotalValueTopicalZ= $dataY4(INVALID)")
                    }
                } else {
                    Log.i (TAG, "#\n TotalValueTopicalY= $dataY3(INVALID)")
                }

            } else {
                Log.i (TAG, "#\n TotalValueTopicalX= $dataY2(INVALID)")
            }
        } else {
            Log.i (TAG, "#\n TotalValueTopicalW= $dataY1(INVALID)")
        }
        return caluMap
    }
    private fun caluProfitday(){
        val XO = caluTotalValueOld()
        val YO = caluTotalValueTopical()
        val keyX1: String? = XO["W"] as String?
        val keyX2: String? = XO["X"] as String?
        val keyX3: String? = XO["Y"] as String?
        val keyX4: String? = XO["Z"] as String?
        val keyY1: String? = YO["W"] as String?
        val keyY2: String? = YO["X"] as String?
        val keyY3: String? = YO["Y"] as String?
        val keyY4: String? = YO["Z"] as String?
        val XTotal = keyX1.toString().toDouble() + keyX2.toString().toDouble() +
                     keyX3.toString().toDouble() + keyX4.toString().toDouble()
        val YTotal = keyY1.toString().toDouble() + keyY2.toString().toDouble() +
                     keyY3.toString().toDouble() + keyY4.toString().toDouble()
        val kap = YTotal - XTotal
        val kapFactorize = "%.2f".format(kap)
        tvprofitdayt.text = kapFactorize
    }
    private fun caluProfitTotalG(): Map<String, Any> {
        val dataOY1 = tvtotalgrowaold.text.toString()
        val dataOY2 = tvtotalgrowbold.text.toString()
        val dataOY3 = tvtotalgrowcold.text.toString()
        val dataOY4 = tvtotalgrowdold.text.toString()
        val dataY1 = tvautotalgrowa.text.toString()
        val dataY2 = tvautotalgrowb.text.toString()
        val dataY3 = tvautotalgrowc.text.toString()
        val dataY4 = tvautotalgrowd.text.toString()
        val caluMap1 = mutableMapOf<String, Any>()
        if (dataY1.isNotEmpty()) {
            caluMap1["W"] = dataY1
        } else {
            caluMap1["W"] = dataOY1
        }
        if (dataY2.isNotEmpty()) {
            caluMap1["X"] = dataY2
        } else {
            caluMap1["X"] = dataOY2
        }
        if (dataY3.isNotEmpty()) {
            caluMap1["Y"] = dataY3
        } else {
            caluMap1["Y"] = dataOY3
        }
        if (dataY4.isNotEmpty()) {
            caluMap1["Z"] = dataY4
        } else {
            caluMap1["Z"] = dataOY4
        }
        val keyW: String? = caluMap1["W"] as String?
        val keyX: String? = caluMap1["X"] as String?
        val keyY: String? = caluMap1["Y"] as String?
        val keyZ: String? = caluMap1["Z"] as String?

        if (keyW != null) {
            if (keyX != null) {
                if (keyY != null) {
                    if (keyZ != null) {
                        val kap =
                            (keyW.toDouble() + keyX.toDouble() + keyY.toDouble() + keyZ.toDouble())
                        val kapFactorize = "%.2f".format(kap)
                        tvprofittotalt.text = kapFactorize
                    } else {
                        Log.i(TAG, "#\n calupProfitTotalGZ= $dataY4(INVALID)")
                    }
                } else {
                    Log.i(TAG, "#\n calupProfitTotalGY= $dataY3(INVALID)")
                }

            } else {
                Log.i(TAG, "#\n calupProfitTotalGX= $dataY2(INVALID)")
            }
        } else {
            Log.i(TAG, "#\n calupProfitTotalGW= $dataY1(INVALID)")
        }
        return caluMap1
    }
    private fun caluMultiGrowG(): Map<String, Any> {
        val dataY1 = tvatmultigrowa.text.toString()
        val dataY2 = tvatmultigrowb.text.toString()
        val dataY3 = tvatmultigrowc.text.toString()
        val dataY4 = tvatmultigrowd.text.toString()
        val caluMap1 = mutableMapOf<String, Any>()
        if (dataY1.isNotEmpty()) {
            caluMap1["W"] = dataY1
        } else {
            caluMap1["W"] = "0"
        }
        if (dataY2.isNotEmpty()) {
            caluMap1["X"] = dataY2
        } else {
            caluMap1["X"] = "0"
        }
        if (dataY3.isNotEmpty()) {
            caluMap1["Y"] = dataY3
        } else {
            caluMap1["Y"] = "0"
        }
        if (dataY4.isNotEmpty()) {
            caluMap1["Z"] = dataY4
        } else {
            caluMap1["Z"] = "0"
        }
        val keyW: String? = caluMap1["W"] as String?
        val keyX: String? = caluMap1["X"] as String?
        val keyY: String? = caluMap1["Y"] as String?
        val keyZ: String? = caluMap1["Z"] as String?

        if (keyW != null) {
            if (keyX != null) {
                if (keyY != null) {
                    if (keyZ != null) {
                        //val kap =
                            (keyW.toDouble() + keyX.toDouble() + keyY.toDouble() + keyZ.toDouble())
                        //val kapFactorize = "%.2f".format(kap)
                        //tvpercentagetotalt.text = kapFactorize
                    } else {
                        Log.i(TAG, "#\n caluMultiGrowGZ= $dataY4(INVALID)")
                    }
                } else {
                    Log.i(TAG, "#\n caluMultiGrowGY= $dataY3(INVALID)")
                }

            } else {
                Log.i(TAG, "#\n caluMultiGrowGX= $dataY2(INVALID)")
            }
        } else {
            Log.i(TAG, "#\n caluMultiGrowGW= $dataY1(INVALID)")
        }
        return caluMap1
    }
    private fun caluPrecentagedaily(){
        val XO = caluTotalValueOld()
        val YO = caluMultiGrowG()
        val keyX1: String? = XO["W"] as String?
        val keyX2: String? = XO["X"] as String?
        val keyX3: String? = XO["Y"] as String?
        val keyX4: String? = XO["Z"] as String?
        val keyY1: String? = YO["W"] as String?
        val keyY2: String? = YO["X"] as String?
        val keyY3: String? = YO["Y"] as String?
        val keyY4: String? = YO["Z"] as String?
        val XTotal = keyX1.toString().toDouble() + keyX2.toString().toDouble() +
                keyX3.toString().toDouble() + keyX4.toString().toDouble()
        val YTotal = keyY1.toString().toDouble() + keyY2.toString().toDouble() +
                keyY3.toString().toDouble() + keyY4.toString().toDouble()
        val kap = YTotal / XTotal * 100
        val kapFactorize = "%.2f".format(kap)
        tvpercentagedayt.text = kapFactorize
    }
    private fun caluPrecentageTotal(){
        val XO = caluTotalInlay()
        val YO = caluProfitTotalG()
        val keyX1: String? = XO["W"] as String?
        val keyX2: String? = XO["X"] as String?
        val keyX3: String? = XO["Y"] as String?
        val keyX4: String? = XO["Z"] as String?
        val keyY1: String? = YO["W"] as String?
        val keyY2: String? = YO["X"] as String?
        val keyY3: String? = YO["Y"] as String?
        val keyY4: String? = YO["Z"] as String?
        val XTotal = keyX1.toString().toDouble() + keyX2.toString().toDouble() +
                keyX3.toString().toDouble() + keyX4.toString().toDouble()
        val YTotal = keyY1.toString().toDouble() + keyY2.toString().toDouble() +
                keyY3.toString().toDouble() + keyY4.toString().toDouble()
        val kap = YTotal / XTotal * 100
        val kapFactorize = "%.2f".format(kap)
        tvpercentagetotalt.text = kapFactorize
    }
    ////Creating_DataPost////
    private fun keyValueDatapost(): MutableMap<String, Any> {
        val abdate = System.currentTimeMillis()
        val acpartyfunda = tvamountaxyz.text.toString()
        val adpartygrowa = tvadpartygrowa.text.toString()
        val aepartyperca = tvaepartyperca.text.toString()
        val afpartyvaluea = tvvaluea.text.toString()
        val agpartyfundb = tvamountbxyz.text.toString()
        val ahpartygrowb = tvadpartygrowb.text.toString()
        val aipartypercb = tvaepartypercb.text.toString()
        val ajpartyvalueb = tvvalueb.text.toString()
        val akpartyfundc = tvamountcxyz.text.toString()
        val alpartygrowc = tvadpartygrowc.text.toString()
        val ampartypercc = tvaepartypercc.text.toString()
        val anpartyvaluec = tvvaluec.text.toString()
        val aopartyfundd = tvamountdxyz.text.toString()
        val appartygrowd = tvadpartygrowd.text.toString()
        val aqpartypercd = tvaepartypercd.text.toString()
        val arpartyvalued = tvvalued.text.toString()
        val astotalfunda = tvastotalfunda.text.toString()
        val atmultigrowa = tvatmultigrowa.text.toString()
        val autotalgrowa = tvautotalgrowa.text.toString()
        val avmultiperca = tvavmultiperca.text.toString()
        val awtotalperca = tvawtotalperca.text.toString()
        val axtotalfundb = tvastotalfundb.text.toString()
        val aymultigrowb = tvatmultigrowb.text.toString()
        val aztotalgrowb = tvautotalgrowb.text.toString()
        val bamultipercb = tvavmultipercb.text.toString()
        val bbtotalpercb = tvawtotalpercb.text.toString()
        val bctotalfundc = tvastotalfundc.text.toString()
        val bdmultigrowc = tvatmultigrowc.text.toString()
        val betotalgrowc = tvautotalgrowc.text.toString()
        val bfmultipercc = tvavmultipercc.text.toString()
        val bgtotalpercc = tvawtotalpercc.text.toString()
        val bhtotalfundd = tvastotalfundd.text.toString()
        val bimultigrowd = tvatmultigrowd.text.toString()
        val bjtotalgrowd = tvautotalgrowd.text.toString()
        val bkmultipercd = tvavmultipercd.text.toString()
        val bltotalpercd = tvawtotalpercd.text.toString()
        val bmtotalabcd = tvtotalvaluet.text.toString()
        val bnmultiprofi = tvprofitdayt.text.toString()
        val bototalprofi = tvprofittotalt.text.toString()
        val bppercentagex = tvpercentagedayt.text.toString()
        val bqpercentagey = tvpercentagetotalt.text.toString()
        //Old//
        val valueaold = tvvalueaold.text.toString()
        val amountaxyz = tvamountaxyzold.text.toString()
        val totalfunda = tvtotalfundaold.text.toString()
        val multigrowa = tvmultigrowaold.text.toString()
        val totalgrowa = tvtotalgrowaold.text.toString()
        val multiperca = tvmultipercaold.text.toString()
        val totalperca = tvtotalpercaold.text.toString()
        val partygrowa = tvpartygrowaold.text.toString()
        val partyperca = tvpartypercaold.text.toString()
        val valuebold = tvvaluebold.text.toString()
        val amountbxyz = tvamountbxyzold.text.toString()
        val totalfundb = tvtotalfundbold.text.toString()
        val multigrowb = tvmultigrowbold.text.toString()
        val totalgrowb = tvtotalgrowbold.text.toString()
        val multipercb = tvmultipercbold.text.toString()
        val totalpercb = tvtotalpercbold.text.toString()
        val partygrowb = tvpartygrowbold.text.toString()
        val partypercb = tvpartypercbold.text.toString()
        val valuecold = tvvaluecold.text.toString()
        val amountcxyz = tvamountcxyzold.text.toString()
        val totalfundc = tvtotalfundcold.text.toString()
        val multigrowc = tvmultigrowcold.text.toString()
        val totalgrowc = tvtotalgrowcold.text.toString()
        val multipercc = tvmultiperccold.text.toString()
        val totalpercc = tvtotalperccold.text.toString()
        val partygrowc = tvpartygrowcold.text.toString()
        val partypercc = tvpartyperccold.text.toString()
        val valuedold = tvvaluedold.text.toString()
        val amountdxyz = tvamountdxyzold.text.toString()
        val totalfundd = tvtotalfunddold.text.toString()
        val multigrowd = tvmultigrowdold.text.toString()
        val totalgrowd = tvtotalgrowdold.text.toString()
        val multipercd = tvmultipercdold.text.toString()
        val totalpercd = tvtotalpercdold.text.toString()
        val partygrowd = tvpartygrowdold.text.toString()
        val partypercd = tvpartypercdold.text.toString()
        val wrapDataPost = mutableMapOf<String, Any>()
        ///Wraping///
        wrapDataPost["aauser"] = signedInUser!!
        wrapDataPost["abdate"] = abdate
        if (acpartyfunda.isNotEmpty()) {
            wrapDataPost["acpartyfunda"] = acpartyfunda
        } else {
            wrapDataPost["acpartyfunda"] = amountaxyz
        }
        if (adpartygrowa.isNotEmpty()) {
            wrapDataPost["adpartygrowa"] = adpartygrowa
        } else {
            wrapDataPost["adpartygrowa"] = partygrowa
        }
        if (aepartyperca.isNotEmpty()) {
            wrapDataPost["aepartyperca"] = aepartyperca
        } else {
            wrapDataPost["aepartyperca"] = partyperca
        }
        if (afpartyvaluea.isNotEmpty()) {
            wrapDataPost["afpartyvaluea"] = afpartyvaluea
        } else {
            wrapDataPost["afpartyvaluea"] = valueaold
        }
        if (agpartyfundb.isNotEmpty()) {
            wrapDataPost["agpartyfundb"] = agpartyfundb
        } else {
            wrapDataPost["agpartyfundb"] = amountbxyz
        }
        if (ahpartygrowb.isNotEmpty()) {
            wrapDataPost["ahpartygrowb"] = ahpartygrowb
        } else {
            wrapDataPost["ahpartygrowb"] = partygrowb
        }
        if (aipartypercb.isNotEmpty()) {
            wrapDataPost["aipartypercb"] = aipartypercb
        } else {
            wrapDataPost["aipartypercb"] = partypercb
        }
        if (ajpartyvalueb.isNotEmpty()) {
            wrapDataPost["ajpartyvalueb"] = ajpartyvalueb
        } else {
            wrapDataPost["ajpartyvalueb"] = valuebold
        }
        if (akpartyfundc.isNotEmpty()) {
            wrapDataPost["akpartyfundc"] = akpartyfundc
        } else {
            wrapDataPost["akpartyfundc"] = amountcxyz
        }
        if (alpartygrowc.isNotEmpty()) {
            wrapDataPost["alpartygrowc"] = alpartygrowc
        } else {
            wrapDataPost["alartygrowc"] = partygrowc
        }
        if (ampartypercc.isNotEmpty()) {
            wrapDataPost["ampartypercc"] = ampartypercc
        } else {
            wrapDataPost["ampartypercc"] = partypercc
        }
        if (anpartyvaluec.isNotEmpty()) {
            wrapDataPost["anpartyvaluec"] = anpartyvaluec
        } else {
            wrapDataPost["anpartyvaluec"] = valuecold
        }
        if (aopartyfundd.isNotEmpty()) {
            wrapDataPost["aopartyfundd"] = aopartyfundd
        } else {
            wrapDataPost["aopartyfundd"] = amountdxyz
        }
        if (appartygrowd.isNotEmpty()) {
            wrapDataPost["appartygrowd"] = appartygrowd
        } else {
            wrapDataPost["appartygrowd"] = partygrowd
        }
        if (aqpartypercd.isNotEmpty()) {
            wrapDataPost["aqpartypercd"] = aqpartypercd
        } else {
            wrapDataPost["aqpartypercd"] = partypercd
        }
        if (arpartyvalued.isNotEmpty()) {
            wrapDataPost["arpartyvalued"] = arpartyvalued
        } else {
            wrapDataPost["arpartyvalued"] = valuedold
        }
        if (astotalfunda.isNotEmpty()) {
            wrapDataPost["astotalfunda"] = astotalfunda
        } else {
            wrapDataPost["astotalfunda"] = totalfunda
        }
        if (atmultigrowa.isNotEmpty()) {
            wrapDataPost["atmultigrowa"] = atmultigrowa
        } else {
            wrapDataPost["atmultigrowa"] = multigrowa
        }
        if (autotalgrowa.isNotEmpty()) {
            wrapDataPost["autotalgrowa"] = autotalgrowa
        } else {
            wrapDataPost["autotalgrowa"] = totalgrowa
        }
        if (avmultiperca.isNotEmpty()) {
            wrapDataPost["avmultiperca"] = avmultiperca
        } else {
            wrapDataPost["avmultiperca"] = multiperca
        }
        if (awtotalperca.isNotEmpty()) {
            wrapDataPost["awtotalperca"] = awtotalperca
        } else {
            wrapDataPost["awtotalperca"] = totalperca
        }
        if (axtotalfundb.isNotEmpty()) {
            wrapDataPost["axtotalfundb"] = axtotalfundb
        } else {
            wrapDataPost["axtotalfundb"] = totalfundb
        }
        if (aymultigrowb.isNotEmpty()) {
            wrapDataPost["aymultigrowb"] = aymultigrowb
        } else {
            wrapDataPost["aymultigrowb"] = multigrowb
        }
        if (aztotalgrowb.isNotEmpty()) {
            wrapDataPost["aztotalgrowb"] = aztotalgrowb
        } else {
            wrapDataPost["aztotalgrowb"] = totalgrowb
        }
        if (bamultipercb.isNotEmpty()) {
            wrapDataPost["bamultipercb"] = bamultipercb
        } else {
            wrapDataPost["bamultipercb"] = multipercb
        }
        if (bbtotalpercb.isNotEmpty()) {
            wrapDataPost["bbtotalpercb"] = bbtotalpercb
        } else {
            wrapDataPost["bbtotalpercb"] = totalpercb
        }
        if (bctotalfundc.isNotEmpty()) {
            wrapDataPost["bctotalfundc"] = bctotalfundc
        } else {
            wrapDataPost["bctotalfundc"] = totalfundc
        }
        if (bdmultigrowc.isNotEmpty()) {
            wrapDataPost["bdmultigrowc"] = bdmultigrowc
        } else {
            wrapDataPost["bdmultigrowc"] = multigrowc
        }
        if (betotalgrowc.isNotEmpty()) {
            wrapDataPost["betotalgrowc"] = betotalgrowc
        } else {
            wrapDataPost["betotalgrowc"] = totalgrowc
        }
        if (bfmultipercc.isNotEmpty()) {
            wrapDataPost["bfmultipercc"] = bfmultipercc
        } else {
            wrapDataPost["bfmultipercc"] = multipercc
        }
        if (bgtotalpercc.isNotEmpty()) {
            wrapDataPost["bgtotalpercc"] = bgtotalpercc
        } else {
            wrapDataPost["bgtotalpercc"] = totalpercc
        }
        if (bhtotalfundd.isNotEmpty()) {
            wrapDataPost["bhtotalfundd"] = bhtotalfundd
        } else {
            wrapDataPost["bhtotalfundd"] = totalfundd
        }
        if (bimultigrowd.isNotEmpty()) {
            wrapDataPost["bimultigrowd"] = bimultigrowd
        } else {
            wrapDataPost["bimultigrowd"] = multigrowd
        }
        if (bjtotalgrowd.isNotEmpty()) {
            wrapDataPost["bjtotalgrowd"] = bjtotalgrowd
        } else {
            wrapDataPost["bjtotalgrowd"] = totalgrowd
        }
        if (bkmultipercd.isNotEmpty()) {
            wrapDataPost["bkmultipercd"] = bkmultipercd
        } else {
            wrapDataPost["bkmultipercd"] = multipercd
        }
        if (bltotalpercd.isNotEmpty()) {
            wrapDataPost["bltotalpercd"] = bltotalpercd
        } else {
            wrapDataPost["bltotalpercd"] = totalpercd
        }
        if (bmtotalabcd.isNotEmpty()) {
            wrapDataPost["bmtotalabcd"] = bmtotalabcd
        } else {
            wrapDataPost["bmtotalabcd"] = "No totalvalue"
        }
        if (bnmultiprofi.isNotEmpty()) {
            wrapDataPost["bnmultiprofi"] = bnmultiprofi
        } else {
            wrapDataPost["bnmultiprofi"] = "No profitday"
        }
        if (bototalprofi.isNotEmpty()) {
            wrapDataPost["bototalprofi"] = bototalprofi
        } else {
            wrapDataPost["bototalprofi"] = "No profittotal"
        }
        if (bppercentagex.isNotEmpty()) {
            wrapDataPost["bppercentagex"] = bppercentagex
        } else {
            wrapDataPost["bppercentagex"] = "No % daily"
        }
        if (bqpercentagey.isNotEmpty()) {
            wrapDataPost["bqpercentagey"] = bqpercentagey
        } else {
            wrapDataPost["bqpercentagey"] = "No % total"
        }
        Log.i(TAG, "$wrapDataPost")
            return wrapDataPost
    }
    private fun saveDatapost(keyValueDatapost: Map<String, Any>) = CoroutineScope(IO).launch {
        try {
            datapostCollectionRef.add(keyValueDatapost).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@ARefreshDataActivity, "Save is Succesfull", Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@ARefreshDataActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}