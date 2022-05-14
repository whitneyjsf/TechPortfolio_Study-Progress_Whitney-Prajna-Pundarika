import replit

baris = [1,2,3,4,5,6,7,8,9]
isi = ["","","","","","","","",""]

def cetak():
  for a in range (2,10):
    if (a+1)%3==0:
      print(baris[a-2],baris[a-1],baris[a])

def check(number,player):
  if isi[number-1] == "":
    inputXO(number,player)
  else:
    print("Sudah terisi.")

def inputXO(number,player):
  if player == 1:
    xo = "X"
  elif player == 2:
    xo = "O"
  baris[number-1] = xo
  isi[number-1] = xo

def checkwin(player):
  if(baris[0]==baris[1] and baris[1]==baris[2]):
    return player
  elif(baris[3]==baris[4] and baris[4]==baris[5]):
    return player
  elif(baris[6]==baris[7] and baris[7]==baris[8]):
    return player
  elif(baris[0]==baris[3] and baris[3]==baris[6]):
    return player
  elif(baris[1]==baris[4] and baris[4]==baris[7]):
    return player
  elif(baris[2]==baris[5] and baris[5]==baris[8]):
    return player
  elif(baris[0]==baris[4] and baris[4]==baris[8]):
    return player
  elif(baris[2]==baris[4] and baris[4]==baris[6]):
    return player
  else:
    return 0

win = 0
player = 2
while(win==0):
  cetak()
  if player == 1:
    player = 2
  elif player == 2:
    player = 1
  if player == 1:
    number = int(input("Player 1 Turn: "))
    check(number,player)
    win = checkwin(player)
  elif player == 2:
    number = int(input("Player 2 Turn: "))
    check(number,player)
    win = checkwin(player)
  replit.clear()
print("Player %d Win" %player)


