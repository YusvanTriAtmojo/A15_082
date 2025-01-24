package com.example.uaspam.ui.navigation

interface DestinasiNavigasi{
    val route: String
    val titleRes: String
}

object DestinasiHomeHewan : DestinasiNavigasi{
    override val route = "homeHwn"
    override val titleRes = "Home Hewan"
}

object DestinasiHome : DestinasiNavigasi{
    override val route = "home"
    override val titleRes = "Home"
}

object DestinasiInsertHewan : DestinasiNavigasi {
    override val route = "insertHwn"
    override val titleRes = "Entry Hewan"
}

object DestinasiDetailHewan : DestinasiNavigasi {
    override val route: String = "detailHwn"
    override val titleRes: String = "Detail Hewan"
    const val IDH = "Id_hewan"
    val routesWithArg = "$route/{$IDH}"
}

object DestinasiUpdateHewan : DestinasiNavigasi {
    override val route: String = "updateHwn"
    override val titleRes: String = "Update Hewan"
    const val IDH = "Id_hewan"
    val routesWithArg = "$route/{$IDH}"
}

object DestinasiHomePetugas : DestinasiNavigasi{
    override val route = "homePtg"
    override val titleRes = "Home Petugas"
}

object DestinasiInsertPetugas : DestinasiNavigasi {
    override val route = "insertPtg"
    override val titleRes = "Entry Petugas"
}

object DestinasiDetailPetugas : DestinasiNavigasi {
    override val route: String = "detailPtg"
    override val titleRes: String = "Detail Petugas"
    const val IDP = "Id_petugas"
    val routesWithArg = "$route/{$IDP}"
}

object DestinasiUpdatePetugas : DestinasiNavigasi {
    override val route: String = "updatePtg"
    override val titleRes: String = "Update Petugas"
    const val IDP = "Id_petugas"
    val routesWithArg = "$route/{$IDP}"
}
