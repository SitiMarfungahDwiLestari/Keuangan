import android.content.Context
import com.example.keuangan.data.DatabaseKeuangan
import com.example.keuangan.repositori.OfflineRepositoriPemasukan
import com.example.keuangan.repositori.OfflineRepositoriPengluaran
import com.example.keuangan.repositori.RepositoriPemasukan
import com.example.keuangan.repositori.RepositoriPengluaran


interface ContainerApp{
    val repositoriPemasukan : RepositoriPemasukan
    val repositoriPengluaran : RepositoriPengluaran
}


class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriPemasukan: RepositoriPemasukan by lazy {
        OfflineRepositoriPemasukan(DatabaseKeuangan.getDatabase(context).PemasukanDao())
    }
    override val repositoriPengluaran: RepositoriPengluaran by lazy {
        OfflineRepositoriPengluaran(DatabaseKeuangan.getDatabase(context).PengluaranDao())
    }

}