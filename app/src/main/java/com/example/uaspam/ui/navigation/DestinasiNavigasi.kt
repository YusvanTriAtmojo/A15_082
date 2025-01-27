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

object DestinasiHomeKandang : DestinasiNavigasi{
    override val route = "homeKdg"
    override val titleRes = "Home Kandang"
}

object DestinasiInsertKandang : DestinasiNavigasi {
    override val route = "insertKdg"
    override val titleRes = "Entry Kandang"
}

object DestinasiDetailKandang : DestinasiNavigasi {
    override val route: String = "detailKdg"
    override val titleRes: String = "Detail Kandang"
    const val IDK = "Id_kandang"
    val routesWithArg = "$route/{$IDK}"
}

object DestinasiUpdateKandang : DestinasiNavigasi {
    override val route: String = "updatekdg"
    override val titleRes: String = "Update Kandang"
    const val IDK = "Id_kandang"
    val routesWithArg = "$route/{$IDK}"
}

object DestinasiHomeMonitoring : DestinasiNavigasi{
    override val route = "homeMtr"
    override val titleRes = "Home Monitoring"
}

object DestinasiInsertMonitoring : DestinasiNavigasi {
    override val route = "insertMtr"
    override val titleRes = "Entry Monitoring"
}

object DestinasiDetailMonitoring : DestinasiNavigasi {
    override val route: String = "detailMtr"
    override val titleRes: String = "Detail Monitoring"
    const val IDM = "Id_monitoring"
    val routesWithArg = "$route/{$IDM}"
}

object DestinasiUpdateMonitoring : DestinasiNavigasi {
    override val route: String = "updatemtr"
    override val titleRes: String = "Update Monitoring"
    const val IDM = "Id_monitoring"
    val routesWithArg = "$route/{$IDM}"
}