import replit


seat = [[' 'for a in range(5)] for a in range(4)]

def printkursi():
  for a in range(4):
    print(seat[a])

def printmenu():
  global seat
  print("1. Booking")
  print("2. Check")
  print("3. Cancel 1 Booking")
  print("4. Cancel All Booking")
  print("5. Exit")
  pilihan = int(input("Input:"))
  if pilihan==1: 
    printkursi()
    inputy = int(input("Baris ke berapa?"))
    inputx = int(input("Kursi ke berapa?"))
    seat[inputy-1][inputx-1]='x'

    printkursi()
    pil2=input("Are you sure?(Y/N)")
    if pil2=='Y':
      printmenu()
     
    elif pil2 =='N':
      seat[inputy-1][inputx-1]=' '
      printmenu()
      

  elif pilihan==2: 
    printkursi()
    print("")
    input("Ketik enter untuk kembali ke menu")
    printmenu()

  elif pilihan==3:
    printkursi()
    inputy = int(input("Baris ke berapa?"))
    inputx = int(input("Kursi ke berapa?"))
    if seat[inputy-1][inputx-1]==" ":
      print("Kursi itu belum dibooking")
      input("\n Ketik enter untuk kembali ke menu")
      printmenu()
    else:
      seat[inputy-1].pop(inputx-1)
      seat[inputy-1].insert(inputx-1," ")
      print("Anda telah membatalkan booking kursi baris ke-%d, kursi ke %d" %(inputy,inputx))
      input("\n Ketik enter untuk kembali ke menu")
      printmenu()

  elif pilihan==4:
    seat = [[' 'for a in range(5)] for a in range(4)]
    print("All booking have been canceled.")
    input("\n Ketik enter untuk kembali ke menu")
    printmenu()

  elif pilihan==5:
    exit
  
  else:
    input("Nomor yang kamu pilih salah, silahkan ulangi lagi.\n\n Ketik enter untuk kembali ke menu")
    printmenu()

printmenu()





