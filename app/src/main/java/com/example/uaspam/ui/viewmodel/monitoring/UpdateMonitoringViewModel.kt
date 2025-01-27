package com.example.uaspam.ui.viewmodel.monitoring

import com.example.uaspam.model.Monitoring
import com.example.uaspam.model.MonitoringFull


fun MonitoringFull.toMonitoring(): Monitoring {
    return Monitoring(
        Id_monitoring = this.Id_monitoring,
        Id_petugas = this.Id_petugas,
        Id_kandang = this.Id_kandang,
        Tanggal_monitoring = this.Tanggal_monitoring,
        Hewan_sakit = this.Hewan_sakit,
        Hewan_sehat = this.Hewan_sehat,
        Status = this.Status
    )
}