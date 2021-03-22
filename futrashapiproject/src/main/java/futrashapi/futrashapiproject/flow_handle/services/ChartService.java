package futrashapi.futrashapiproject.flow_handle.services;

import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.repository.ChartRepository;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChartService {

    private ChartRepository chartRepository;

    private ItemRepository itemRepository;

    public ChartService(ChartRepository chartRepository, ItemRepository itemRepository) {
        this.chartRepository = chartRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Create a new role along with users
     */

    @Transactional
    public ResponseEntity<Object> addChart(Chart chart) {

        Chart newChart = new Chart();
        newChart.setImage_url(chart.getImage_url());
        newChart.setJenis_makanan(chart.getJenis_makanan());
        newChart.setTidak_dikonsumsi_sejak(chart.getTidak_dikonsumsi_sejak());
        newChart.setDijual_karena(chart.getDijual_karena());
        newChart.setBerat_makanan(chart.getBerat_makanan());
        newChart.setNama_toko(chart.getNama_toko());
        newChart.setNama_penjual(chart.getNama_penjual());
        newChart.setLokasi_makanan(chart.getLokasi_makanan());
        newChart.setHarga_makanan(chart.getHarga_makanan());
        newChart.setSaran_penggunaan(chart.getSaran_penggunaan());
        newChart.setKandungan_kimia(chart.getKandungan_kimia());


        //newRole.setUsers(role.getUsers());
        newChart.setItems(chart.getItems());
        //Role savedRole = roleRepository.save(newRole);
        Chart savedChart=chartRepository.save(newChart);
        if (chartRepository.findById(savedChart.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Chart");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Chart");
    }

    /**
     * Delete a specified role given the id
     */
    public ResponseEntity<Object> deleteChart(Long id) {
        if (chartRepository.findById(id).isPresent()) {
            chartRepository.deleteById(id);
            if (chartRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
}
