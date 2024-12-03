$(document).ready(function () {
    var id, namaLokasi, kodeLokasi, page;

    function getInputValue() {
        id = $("#id").val();
        namaLokasi = $("#namaLokasi").val();
        kodeLokasi = $("#kodeLokasi").val();
    }
    if(localStorage.getItem('user')) {
        $('.usernameHello').html(JSON.parse(localStorage.getItem('user')).username);
    }
    
    setInterval(function() {
        $('.dateTag').html(new Date().toLocaleString());
    }, 1000);
    $("#btnCancel").on('click', function () {
        $("#myModal").hide();
    });

    $('#btnAdd').click(function () {
        $("#myModal").show();
        $("#titel1").show();
        $("#titel2").hide();
        $("#namaLokasi").prop('disabled', false);
        page = "tambah";
    });

    $("#tabelsiswa tbody").on('click', '#btnDel', function () {
        var baris = $(this).closest('tr');
        var id = $('#tabelsiswa').DataTable().row(baris).data().id; // Mendapatkan id dari data tabel
        var Nama = $('#tabelsiswa').DataTable().row(baris).data().namaLokasi; // Mendapatkan namaLokasi dari data tabel
        
        if (confirm("Anda yakin data '" + id + " - " + Nama + "' akan dihapus?")) {
            $.post("/absensi/LokasiCtr", 
            {
                page: "hapus",
                id: id
            },
            function (data, status) {
                console.log(data);
                alert(data);
                location.reload();
            });
        }
    });
    
    $("#tabelsiswa tbody").on('click', '#btnEdit', function () {
        $("#myModal").show(); // Mengubah 'modal('show')' menjadi 'show()' untuk konsistensi
        $("#titel1").hide();
        $("#titel2").show();
        $("#role").prop('disabled', true); // Menggunakan 'kodeLokasi' untuk konsistensi dengan file jurusan

        // Mengambil data dari baris yang diklik
        var baris = $(this).closest('tr');
        var id = $('#tabelsiswa').DataTable().row(baris).data().id;

        $.post("/absensi/LokasiCtr",
            {
                page: "tampil",
                id: id
            },
            function (data, status) {
                $("#id").val(data.id);
                $("#namaLokasi").val(data.namaLokasi);
                $("#kodeLokasi").val(data.kodeLokasi);
            },
            "json"
        );
        page = "edit"; // Menambahkan 'page' variable assignment setelah ajax call
    });

    $("#btnSave").on('click', function () {
        getInputValue();
        if (namaLokasi === "") {
            alert("namaLokasi harus diisi");
            $("#namaLokasi").focus();
        } else if (kodeLokasi === "") {
            alert("kodeLokasi harus diisi");
            $("#kodeLokasi").focus();
        } else {
            $.post("/absensi/LokasiCtr",
                {
                    page: page,
                    id: id,  // Tambahkan id di sini
                    namaLokasi: namaLokasi,
                    kodeLokasi: kodeLokasi,
                },
                function (data, status) {
                    //console.log(data);
                    alert(data);
                    location.reload();
                });
        }
    });

    $.ajax({
        url: "/absensi/LokasiCtr",
        method: "GET",
        dataType: "json",
        success: function(data){
            $("#tabelsiswa").dataTable({
                serverside: true,
                processing: true,
                data: data,
                sort: true,
                searching: true,
                paging: true,
                columns: [
                    {'data': 'namaLokasi'},
                    {'data': 'id', 'visible': false},
                    {'data': 'kodeLokasi'},
                    {
                        'data': null,
                        'className': 'dt-right',
                        'mRender': function (data, type, row) {
                            return "<a class='btn btn-outline-success btn-sm' id='btnEdit'>Edit</a>"
                                + "&nbsp;&nbsp;"
                                + "<a class='btn btn-outline-danger btn-sm' id='btnDel'>Hapus</a>";
                        }
                    }
                ]
            });
        }
    });
});