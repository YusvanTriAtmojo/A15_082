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