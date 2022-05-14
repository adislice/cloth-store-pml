/*
SQLyog Ultimate v12.5.1 (32 bit)
MySQL - 10.4.11-MariaDB : Database - db_pakaian
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_pakaian` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_pakaian`;

/*Table structure for table `detail_transaksi` */

DROP TABLE IF EXISTS `detail_transaksi`;

CREATE TABLE `detail_transaksi` (
  `id_transaksi` int(11) unsigned NOT NULL,
  `id_produk` int(11) unsigned NOT NULL,
  `qty` int(11) unsigned DEFAULT NULL,
  `total` int(11) unsigned DEFAULT NULL,
  KEY `id_produk` (`id_produk`),
  KEY `id_transaksi` (`id_transaksi`),
  CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `detail_transaksi` */

/*Table structure for table `diskon` */

DROP TABLE IF EXISTS `diskon`;

CREATE TABLE `diskon` (
  `id_diskon` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  `diskon_persen` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_diskon`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `diskon` */

insert  into `diskon`(`id_diskon`,`nama`,`diskon_persen`) values 
(1,'Tidak ada',0),
(2,'Promo Bulan Mei',10);

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `id_kategori` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kategori` */

insert  into `kategori`(`id_kategori`,`nama`) values 
(1,'Busana Wanita'),
(2,'Sweater'),
(3,'Denim'),
(4,'Jeans'),
(5,'Rok'),
(6,'Jacket');

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `id_produk` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  `id_kategori` int(11) unsigned NOT NULL,
  `harga` int(11) unsigned DEFAULT 0,
  `stok` int(11) unsigned DEFAULT 0,
  `id_diskon` int(11) unsigned DEFAULT NULL,
  `gambar` text DEFAULT NULL,
  `deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`id_produk`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_diskon` (`id_diskon`),
  CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`),
  CONSTRAINT `produk_ibfk_2` FOREIGN KEY (`id_diskon`) REFERENCES `diskon` (`id_diskon`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

/*Data for the table `produk` */

insert  into `produk`(`id_produk`,`nama`,`id_kategori`,`harga`,`stok`,`id_diskon`,`gambar`,`deskripsi`) values 
(1,'FRAGILE - Atasan Rajut Ukuran M',1,181000,2,1,'https://ksufmvei.sirv.com/cloth-store/m47200194161_1.jpg','Atasan kemeja rajut FRAGILE\r\nLengan topi Lengan topi\r\n\r\n[Ukuran] Notasi 38\r\nUkuran datar\r\n[Panjang] kira-kira 55 cm [Lebar] kira-kira 34 cm\r\n[Panjang lengan] Kira-kira 11 cm [Lebar bahu] Kira-kira 32 cm\r\n\r\n[Bahan] [Tubuh utama] 48% katun\r\nAkrilik 42%\r\nnilon 8%\r\nPoliuretan 2%\r\n[Kain terpisah] 100% sutra\r\n\r\n[Negara] produk BEKAS\r\nAda perasaan berguna, tetapi tidak terlalu terlihat\r\nTidak ada jiji dan kondisinya baik.\r\n\r\nWarna hitam\r\nPanjang lengan... Lengan pendek\r\nPola / desain ... polos\r\nPerasaan musiman ... Musim semi, musim panas\r\n\r\nCatatan】\r\nSetelah pembelian, disimpan di rumah.\r\nKarena pengukuran dan inspeksi amatir, kesalahan dan kelalaian kecil\r\nMungkin ada hal-hal seperti itu.\r\nFoto-foto yang ditampilkan adalah untuk monitor dan pengaturan lingkungan Anda.\r\nWarna terlihat sedikit berbeda dari produk yang sebenarnya tergantung pada produk.\r\nMungkin saja.\r\nKarena akan dikirim dalam keadaan terlipat, maka akan dilipat.\r\nMungkin ada kerutan dll.\r\n\r\nTerima kasih atas pengertian Anda.'),
(2,'FRAGILE - Sweater Rajut Ukuran 38',1,430000,3,1,'https://ksufmvei.sirv.com/cloth-store/m60346299519_1.jpg','Panjang lengan ... lengan panjang\r\nPola / desain ... polos\r\nPerasaan musiman ... Musim semi, musim gugur, musim dingin\r\nWarna hitam\r\nPanjang ... Tengah\r\nSemua produk bisa langsung dibeli\r\n\r\n* Pastikan untuk memeriksa gambar terakhir. Rincian kondisi dan ukuran dijelaskan.\r\n* Klasifikasi ukuran seperti M dan L dapat diatur dengan menilai dari dimensi yang diukur. Pastikan untuk memeriksa dimensi gambar yang diukur sebelum membeli.\r\n\r\n\r\n#nao Profil diskon lanjutan harus dibaca Anda dapat melihat semua produk\r\n\r\n#naoshop atasan\r\n\r\n#naoshop rajutan\r\n\r\n\r\nIkuti diskon\r\nAda diskon untuk followers saja!\r\n\r\n2000 yen ~ 200 yen OFF\r\n3000 yen ~ 300 yen OFF\r\n4000 Yen ~ 400 Yen OFF\r\n5000 yen ~ 500 yen OFF\r\n\r\n\r\nDiskon pembelian grosir\r\nDiskon 500 yen untuk 2 poin\r\nOFF 1000 yen untuk 3 poin atau lebih\r\n\r\n\r\nDiskon Pengulang\r\nDiskon 200 yen hanya untuk repeater!\r\n* Harap dicatat bahwa itu tidak dapat digunakan dalam kombinasi dengan Diskon Ikuti.\r\n\r\n\r\nJika Anda ingin menerima diskon, pastikan untuk berkomentar \"Sebelum membeli\" sebagai diskon lanjutan atau diskon repeater. Jumlah tidak dapat diubah setelah pembelian m (_ _) m'),
(3,'FRAGILE - Oversized Wool Sweater Dewasa Elegan Sederhana',2,210000,3,1,'https://ksufmvei.sirv.com/cloth-store/m98689137738_1.jpg','Merek\r\nFRAGILE\r\n\r\nWarna\r\nAbu-abu\r\n\r\nBahan (Detail ada di foto)\r\n\r\nUkuran\r\nukuran 38\r\nPanjang sekitar 80 cm Lebar sekitar 40 cm\r\nLebar bahu kira-kira 41 cm Panjang lengan kira-kira 53 cm\r\n\r\nNegara (subyektif)\r\nTidak ada goresan atau noda, tetapi ada pil secara keseluruhan.\r\n\r\nCatatan\r\nPastikan untuk memilih ukuran berdasarkan [nilai numerik] pengukuran.\r\nHarap dicatat bahwa beberapa kesalahan mungkin terjadi karena pengukuran amatir.\r\nKategori pria, wanita, dan juga memiliki bagian subjektif.\r\nBahkan di tengah pesan, orang yang menekan tombol beli lebih dulu diprioritaskan.\r\nMeskipun kami telah mengkonfirmasinya pada saat daftar, harap dicatat bahwa goresan kecil dan kotoran mungkin terlewatkan.\r\nUntuk mengurangi biaya pengiriman, kami dapat mengubah penerbangan Yu Yu Mercari. perhatikan itu.\r\nUntuk mengurangi biaya pengiriman, kami dapat menggunakan tas terkompresi. perhatikan itu.'),
(4,'FRAGILE - Sweater Rajut Panjang Tiga Perempat',1,150000,4,1,'https://static.mercdn.net/item/detail/orig/photos/m54979059055_1.jpg?1652362416','Lebar bahu 40 cm\r\nPanjang lengan 39 cm\r\nLebar 44 cm\r\nPanjang 57,5 ​​cm'),
(5,'Baju Rajut Lengan Pendek dengan Kerah Sailor Suit Biru Tua',1,230000,3,1,'https://static.mercdn.net/item/detail/orig/photos/m29406079092_1.jpg?1651629596','Panjang ... Tengah\r\nPanjang lengan... Lengan pendek\r\nPerasaan musiman ... Musim semi, musim panas\r\nBahan ... 46% katun\r\nPola / desain ... polos\r\nLeher... Bukaan yang membuat tulang selangka terlihat cantik\r\nWarna... Biru Navy Biru'),
(6,'Vivienne Westwood Denim Roll-up Celana Pergelangan Kaki Bordiran Panjang Tiga Perempat',4,570000,2,1,'https://static.mercdn.net/item/detail/orig/photos/m65978396508_1.jpg?1651736340','Vivienne Westwood\r\nLabel merah\r\nCelana denim gulung\r\nKegunaannya normal\r\nBordir bola kotak-kotak\r\n\r\nUkuran 2\r\nWarna biru\r\nbuatan Jepang\r\nbahan\r\nkatun 100%\r\n\r\nUkuran datar\r\nPanjang 70\r\npinggang 38\r\nNaik 20\r\nInseam 50'),
(7,'Vivienne Westwood Jeans Jeans Indigo Orb Bordir Ukuran 1',4,900000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m40642451386_1.jpg?1650538214','Vivienne Westwood RED LABEL\r\n\r\n·barang·\r\nJeans jeans celana denim\r\nBordir jahitan orb\r\nTombol terukir bola\r\nDenim lurus longgar\r\nbuatan Jepang\r\n\r\n·Warna·\r\nBiru muda nila\r\n\r\n·ukuran·\r\nUkuran 1 (setara dengan ukuran S)\r\n\r\nDimensi (sekitar Cm)\r\nPinggang 37 Panjang 88 Hem lebar 20\r\nNaik 19 Inseam 66 Lebar 26'),
(8,'Vivienne Westwood Celana Denim',4,980000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m52385732369_1.jpg?1649811973','Ukuran 1 ukuran (datar)\r\nLingkar pinggang 37 cm\r\nPinggul 41 cm\r\nNaik 22 cm\r\nJahitan 71 cm\r\nWatari 22,5 cm\r\nMulut kelim 14,5 cm\r\n\r\nBahan: \r\n95% katun\r\n5% poliuretan'),
(9,'Vivienne Westwood Red Label Denim',4,560000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m13451739216_1.jpg?1643179806','Vivienne Westwood RED LABEL\r\n\r\nUkuran: 1/Tanpa peregangan\r\nPinggang: sekitar 35 cm\r\nNaik: Sekitar 21 cm\r\nInseam: Sekitar 72 cm'),
(10,'New Balance Atasan Wanita Spoty Warna Biru',1,115000,2,1,'https://static.mercdn.net/item/detail/orig/photos/m44437031324_1.jpg?1651393822','Ukuran: 1/Tanpa peregangan\r\nPinggang: sekitar 35 cm\r\nNaik: Sekitar 21 cm\r\nInseam: Sekitar 72 cm'),
(12,'Burberry Blue Label Rok Nova Gingham Check',5,900000,2,1,'https://static.mercdn.net/item/detail/orig/photos/m21381891988_1.jpg?1651752576','Informasi produk\r\nRok Burberry Blue Label.\r\nBerlipit dengan pola gingham check Nova kerajaan.\r\n\r\nPola warna\r\nGingham Check Nova\r\n\r\nUkuran (Satuan: cm) / Notasi\r\nukuran 36\r\nPanjang 40\r\npinggang 66'),
(14,'Celana Pendek Tweed Free Zone',1,270000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m97216049307_1.jpg?1651131337','Ukuran (Satuan: cm) / Notasi\r\nukuran 36\r\nPanjang 40\r\npinggang 66'),
(15,'L\'EST ROSE Gaun Pola Bunga Kotak-kotak Musim Semi / Musim Panas',1,500000,2,1,'https://static.mercdn.net/item/detail/orig/photos/m43540024457_1.jpg?1652407185','Perasaan musiman ... Musim semi, musim panas\r\nPanjang lengan... tanpa lengan\r\nPola / desain ... pola bunga\r\n\r\nIni adalah gaun Rest Rose.\r\nIni adalah desain yang bagus dengan siluet feminin.\r\nKenyamanan luar biasa dan seperti merek\r\nKualitas kain, bahan dan jahitan bagus.\r\nTampaknya aktif di musim mendatang (* -`)\r\nUkurannya adalah ukuran M.\r\nUntuk detailnya, silakan lihat panjang dan lebarnya.\r\n\r\nPanjang: Sekitar 93 cm\r\nLebar: sekitar 43 cm'),
(16,'L\'EST ROSE One Piece',1,485000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m78290955008_1.jpg?1651829546','Ukuran M, lebar 36 cm, panjang 85 cm, lebar bahu 31 cm, panjang lengan 30 cm.'),
(17,'L\'EST ROSE Dress Hitam Lipit Renda Kamisol Ukuran S',1,610000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m10322858278_1.jpg?1651474675','● Nama merek\r\nL\'EST ROSE\r\n\r\n● Warna\r\nHitam hitam\r\n\r\n● Ukuran: S\r\nPanjang total: Sekitar 90\r\nLebar: sekitar 34\r\n* Semua ukuran diukur secara horizontal.\r\nUntuk pengukuran amatir, mohon maafkan beberapa kesalahan.\r\n\r\n● Desain\r\nGaun kamisol cantik dengan shirring di dada dan lipit halus di rok.\r\nPita itu lucu apakah Anda mengikatnya di depan atau di belakang.\r\nTidak ada ritsleting, dan ini adalah jenis yang bisa Anda pakai apa adanya.\r\n\r\n● Bahan\r\n100% poliester%\r\n\r\n● Kondisi\r\nPakai sekali.\r\nTidak ada kotoran atau kerusakan yang terlihat.'),
(18,'L\'EST ROSE Floral Dress Tanpa Lengan',1,320000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m42730748837_1.jpg?1651966381','● Ukuran: Ukuran M\r\n\r\n● Bahan:\r\nBahan luar 97% poliester / 3% poliuretan\r\nLapisan poliester 100%\r\n\r\n● Kondisi:\r\nTidak ada goresan yang terlihat.'),
(19,'L\'EST ROSE One Piece',1,770000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m24905349201_1.jpg?1639662901','Ukuran M\r\n\r\nTali bahu satin panjang yang dapat dilepas dan dapat disesuaikan\r\nKarena ini adalah ritsleting horizontal, mudah dipasang dan dilepas.'),
(20,'L\'EST ROSE Dress Tanpa Lengan Ukuran 2 M Set Dalam Putih Panjang',1,770000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m18164878571_1.jpg?1651067665','L\'EST ROSE lace organdy beige kerah frill pita layering\r\n\r\n[Penempatan datar ukuran sebenarnya (satuan: cm)]\r\n\r\nUkuran: 2\r\n\r\nPanjang: 75\r\n\r\nLebar: 36\r\n\r\nPinggang: 39\r\n\r\nLebar bahu: 30\r\n\r\nPanjang lengan: Tidak ada'),
(21,'HERILL 22ss Jacket Weekend NEW Warna Blue Navy',6,4400000,1,1,'https://static.mercdn.net/item/detail/orig/photos/m85062866189_1.jpg?1650211616','Ukuran 1\r\nWarna Biru Navy');

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` int(11) unsigned NOT NULL,
  `tanggal` datetime DEFAULT NULL,
  `subtotal` int(11) unsigned DEFAULT NULL,
  `penerima` varchar(255) DEFAULT NULL,
  `alamat_pengiriman` text DEFAULT NULL,
  `no_telp_penerima` varchar(18) DEFAULT NULL,
  `status` enum('Belum bayar','Dikemas','Dikirim','Diterima') DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `transaksi` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id_user` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `no_telp` varchar(18) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
