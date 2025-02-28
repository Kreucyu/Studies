/* Main.c file generated by New Project wizard
 *
 * Created:   qui ago 8 2024
 * Processor: 8086
 * Compiler:  Digital Mars C
 *
 * Before starting simulation set Internal Memory Size 
 * in the 8086 model properties to 0x10000
 */

#define IO0 0x0000
#define IO1 0x0200
#define IO2 0x0400
#define IO3 0x0600
#define IO4 0x0800
#define IO5 0x0A00
#define IO6 0x0C00
#define IO7 0x0E00
#define IO8 0x1000
#define IO9 0x1200
#define IO10 0x1400
#define IO11 0x1600
#define IO12 0x1800
#define IO13 0x1a00
#define IO14 0x1c00
#define IO15 0x1e00

// 8251A USART 
// USART = UNIVERSAL SERIAL ASSYNCRONOUS RECEIVER TRANSMITER

#define ADR_USART_DATA   (IO6 + 00h)
//ONDE VOCE VAI MANDAR E RECEBER DADOS DO 8251

#define ADR_USART_CMD   (IO6 + 02h)
//É O LOCAL ONDE VOCE VAI ESCREVER PARA PROGRAMAR O 8251
//WRITE

#define ADR_USART_STAT  (IO6 + 02h)
//RETORNA O STATUS SE UM CARACTER FOI DIGITADO NO TERMINAL
//RETORNA O STATUS SE POSSO TRANSMITIR CARACTER PARA O TERMINAL
//READ

//ENDEREÇOS DOS REGISTRADORES DO 8255
#define ADR_PPI_PORTA	  (IO7)
#define ADR_PPI_PORTB	  (IO7 + 02h)
#define ADR_PPI_PORTC	  (IO7 + 04h)
#define ADR_PPI_CONTROL    (IO7 + 06h)

#define PPI_PORTA_INP	  10h //00010000
#define PPI_PORTA_OUT	  00h
#define PPI_PORTB_INP	  02h
#define PPI_PORTB_OUT	  00h
#define PPI_PORTCL_INP	  01h //00000001
#define PPI_PORTCL_OUT	  00h
#define PPI_PORTCH_INP	  08h
#define PPI_PORTCH_OUT	  00h
#define PPI_MODE_BCL_0	  00h
#define PPI_MODE_BCL_1	  04h
#define PPI_MODE_ACH_0	  00h
#define PPI_MODE_ACH_1	  20h  //00100000
#define PPI_MODE_ACH_2	  40h  //01000000
#define PPI_ACTIVE	  80h  //10000000

void MANDAR_CARACTER(void);
void LER_TECLADO(void);
unsigned char HORA;
unsigned char MINUTO;
unsigned char NUMERO1;
unsigned char NUMERO2;
unsigned char OPERADOR;

#define TAM_STRING 64
#define TAM_BYTE 2
char Mensagem1[] = {"MIGUEL ALEXANDRE WISINTAINER"};
char Mensagem2[TAM_STRING+1];
unsigned char Contador_Teclas_Digitadas;
char NUMERO[3];


char Mens1[] = "Entre com o numero da tabuada";
char Mens2[] = " * ";
char Mens3[] = " = ";
char Mens4[] = "Numero nao pode ser maior que 9";


char Mens5[] = "O resultado nao pode ultrapassar 99";
char Mens6[] = "O resultado nao pode ser menor que 0";
char Mens7[] = "Nao pode ter divisao por 0";
char Num_Tab;

char Tecla;
char num_dig = 0;
void SELECIONA_DISPLAY_1(void);
void SELECIONA_DISPLAY_2(void);
void MANDA_PORT_A(void);
void MANDAR_PORT_B(void);
void EXIBIR_DISPLAY_1(void);

void EXIBIR_DISPLAY_2(void);

void EXIBIR_DISPLAY_3(void);

void EXIBIR_DISPLAY_4(void);

//---------------------------------------------------------
// ROTINAS PARA 8255   
void INICIALIZA_8255(void)
{
   _asm {
   PUSHF
   PUSH AX
   PUSH DX
   MOV DX, ADR_PPI_CONTROL
   MOV AL,0
   OR AL,PPI_PORTA_OUT
   OR AL,PPI_PORTB_OUT  
   OR AL,PPI_PORTCL_INP        
   OR AL,PPI_PORTCH_INP
   OR AL,PPI_MODE_BCL_0
   OR AL,PPI_MODE_ACH_0
   OR AL,PPI_ACTIVE
   OUT DX,AL
   POP DX
   POP AX
   POPF
   }      
}
//---------------------------------------------------------
// trabalho parte 2   

//digitar 21 
//digitar operador
//mostrar operador e num 1
//digitar numero 2 e só isso no display
//a parte do igual tem que continuar mostrando


char INDICE_DISPLAY = 1;
char digito_dez;
char digito_uni;

/* NMI Interrupt Handler */
// Dicas
// Seja o mais breve possivel na execucao!
void _interrupt _far nmi_handler(void)
{     
	asm{
	cmp INDICE_DISPLAY, 1 
	je HABILITA_DISPLAY_1
	cmp INDICE_DISPLAY, 2
	je HABILITA_DISPLAY_2
	jmp sai_interrupcao
	
     

HABILITA_DISPLAY_1:
			CALL SELECIONA_DISPLAY_1
			cmp num_dig, 0
			je EXIBIR_DISPLAY_1
			cmp num_dig, 1
			je EXIBIR_DISPLAY_3
			call MANDAR_PORT_B
			MOV INDICE_DISPLAY, 2
			jmp sai_interrupcao
			
HABILITA_DISPLAY_2:
			CALL SELECIONA_DISPLAY_2
			cmp num_dig, 0
			je EXIBIR_DISPLAY_2
			cmp num_dig, 1
			je EXIBIR_DISPLAY_4
			call MANDAR_PORT_B
			MOV INDICE_DISPLAY, 1
			jmp sai_interrupcao


void EXIBIR_DISPLAY_1(void)
{
	mov ah, 0
	mov al, NUMERO1
	mov dl, 10
	div dl
	}
	
	
void EXIBIR_DISPLAY_2(void)
{
	mov ah, 0
			mov al, NUMERO1
			mov dl, 10
			div dl
			mov al, ah
	}
	
void EXIBIR_DISPLAY_3(void)
{
	mov ah, 0
	mov al, NUMERO2
	mov dl, 10
	div dl
	}
	
	
void EXIBIR_DISPLAY_4(void)
{
	mov ah, 0
			mov al, NUMERO2
			mov dl, 10
			div dl
			mov al, ah
	}

sai_interrupcao:
}

//ESTA ROTINA ALTERA O VETOR DE INTERRUPCAO INT_NO PARA QUE APONTE PARA SERVICE_PROC
void set_int(unsigned char int_no, void * service_proc)
{ asm
    { push es
      xor ax, ax  //zera ax
      mov es, ax  // manda ES aponta para SEGMENTO 0
      mov al, int_no //pega no numero da interrupcao 2
      xor ah, ah     //zera ah //ax 0000000000000010
      shl ax, 1      //shift left rotaciona esquerda 0000000000000010
                                                   //0000000000000100
                                                   //0000000000001000       
      shl ax, 1      //shift left rotaciona esquerda
      mov si, ax  //manda si apontar para endereco 8
      mov ax, service_proc //pega o endereco da tratadora
      mov es:[si], ax //escreve na memoria a partir de 8 
      inc si //
      inc si //
      mov bx, cs //segmento onde esta a tua rotina tratadora, seg 0 (Code Segmento)
      mov es:[si], bx //escreve segmento
      pop es  //gravamos entao em 8,9,10,11 o endereco do tratador e o segmento onde ele se encontra
    }
 }


void NORMAL(void){

}

void MANDAR_PORT_B(void)
{
   _asm {
    PUSHF
    PUSH DX
    MOV DX,ADR_PPI_PORTB
    OUT DX,AL
    POP DX
    POPF
   }
}

void SELECIONA_DISPLAY_1(void)
{  
   asm {
      mov dx, IO8
      mov al, 0b10
      out dx, al     
   }   
}

void SELECIONA_DISPLAY_2(void)
{  
   asm {
       mov dx, IO8
      mov al, 0b01
      out dx, al      
   }   
}


	
		








//-----------------------------
//-----------------------------

//Esta rotina escreve em PORT A o valor do registrador AL
void MANDA_PORT_A(void)
{
   _asm
   {
    PUSHF
    PUSH DX
    MOV DX,ADR_PPI_PORTA
    OUT DX,AL
    POP DX
    POPF
   }
}

//esta funcao retorna em AL o conteudo lido da entrada PORT C
void LE_PORT_C(void)
{
   _asm {
    PUSHF
    PUSH DX
    MOV DX,ADR_PPI_PORTC
    IN AL,DX
    POP DX
    POPF
   }
}


//nao tem parametro, basta chamar para pular linha
void PULAR_LINHA(void)
{ asm 
	{
		pushf
		push ax
		mov al, 13
		call MANDAR_CARACTER
		mov al, 10
		call MANDAR_CARACTER
		pop ax
		popf
	}
}

//Esta funcao imprimira um string apontada por BX
void MANDAR_STRING(void)
{
	asm {
		pushf
		push ax
COMPARA:
		mov al, [bx]
		cmp al, 0 //compara se é NULL
		je SAI_MANDAR_STRING
		call MANDAR_CARACTER
		inc bx
		jmp COMPARA
SAI_MANDAR_STRING:
		pop ax
		popf
	}
}

void RECEBER_CARACTER(void);

void RECEBER_STRING(void)
{
	asm {
			pushf
			push ax
			mov Contador_Teclas_Digitadas, 0
le_caracter:
			call RECEBER_CARACTER
			cmp al, 13 //se pressionou enter
			je TRATA_CR
			cmp al, 8  //ou backspace
			je TRATA_BS
			cmp Contador_Teclas_Digitadas, TAM_STRING
			je le_caracter
			mov [bx], al
			inc bx
			inc Contador_Teclas_Digitadas
			call MANDAR_CARACTER
			jmp le_caracter			
TRATA_CR:
			mov byte ptr [bx],0  //coloca null
			jmp SAI_RECEBER_STRING
TRATA_BS:
			cmp Contador_Teclas_Digitadas, 0
			je le_caracter
			dec bx
			mov byte ptr [bx], 0 //opcional
			dec Contador_Teclas_Digitadas
			mov al, 8 //backspace
			call MANDAR_CARACTER
			jmp le_caracter
SAI_RECEBER_STRING:
			pop ax
			popf
	}
}

void RECEBER_BYTE(void)
{
	asm {
			pushf
			push cx //cl filho
			mov Contador_Teclas_Digitadas, 0
			mov bx, OFFSET NUMERO
le_caracter:
			call RECEBER_CARACTER
			cmp al, 13 //se pressionou enter
			je TRATA_CR
			cmp al, 8  //ou backspace
			je TRATA_BS
			cmp Contador_Teclas_Digitadas, TAM_BYTE
			je le_caracter
			cmp al,'0'
			jl le_caracter
			cmp al,'9'
			jg le_caracter
			mov [bx], al
			inc bx
			inc Contador_Teclas_Digitadas
			call MANDAR_CARACTER
			jmp le_caracter			
TRATA_CR:
			cmp Contador_Teclas_Digitadas,2
			jne le_caracter
			mov byte ptr [bx],0  //coloca null
			jmp SAI_RECEBER_STRING
TRATA_BS:
			cmp Contador_Teclas_Digitadas, 0
			je le_caracter
			dec bx
			mov byte ptr [bx], 0 //opcional
			dec Contador_Teclas_Digitadas
			mov al, 8 //backspace
			call MANDAR_CARACTER
			jmp le_caracter
SAI_RECEBER_STRING:
			dec bx
			dec bx
			sub byte ptr [bx],48  //2
			inc bx
			sub byte ptr [bx],48  //3
			dec bx
			mov al, [bx]
			mov cl, 10
			mul cl
			inc bx
			add ax, byte ptr [bx]
			pop cx
			popf
	}
}

//certificar que AL tenha o numero a ser impresso
//AL = 17
void MANDAR_NUMERO(void)
{
 _asm
 {
      pushf
      push ax //pq estas salvando al, se al é parametro ? na verdade estou salvando ah
      push bx
      //DIV 
      //DIV XX
      //AX <= AX / XX ---> RESTO --> AH       QUOCIENTE ----> AL
      mov ah, 0
      mov bh, 10
      div bh
      // AX / bh ===> teremos em AH = 7 e em AL = 1
      add al,'0' // 1 ---> "1"
      call MANDAR_CARACTER
      add ah,'0' // 7 ---> "7"
      mov al,ah // move "7" para Al para ser impresso!!!!!!!!!!!!!
      call MANDAR_CARACTER
      pop bx
      pop ax
      popf
 }  
}

//19200,8,N,1
void INICIALIZA_8251(void)
{
   _asm {
   MOV AL,0
   MOV DX, ADR_USART_CMD
   OUT DX,AL
   OUT DX,AL
   OUT DX,AL
   MOV AL,40H
   OUT DX,AL
   MOV AL,4DH
   OUT DX,AL
   MOV AL,37H
   OUT DX,AL
      }
}
//TENHA CERTEZA QUE O CARACTER ESTEJA EM AL
//AL É A PASSAGEM DE PARAMETRO - (POR REGISTRADOR)
void MANDAR_CARACTER(void)
{
   _asm {
      PUSHF  
      PUSH DX
      PUSH AX  
BUSY:
      MOV DX, ADR_USART_STAT
      IN  AL,DX
      TEST AL,1
      JZ BUSY
      MOV DX, ADR_USART_DATA
      POP AX  
      OUT DX,AL
      POP DX
      POPF 
   }  
}

//AO TERMINO DESTA ROTINA, TEREMOS EM AL
//O CODIGO ASCII DA TECLA DIGITADA
void RECEBER_CARACTER(void)
{
   _asm {
      PUSHF
      PUSH DX
AGUARDA_CARACTER:
      MOV DX, ADR_USART_STAT
      IN  AL,DX
      TEST AL,2
      JZ AGUARDA_CARACTER
      MOV DX, ADR_USART_DATA
      IN AL,DX
      SHR AL,1 
NAO_RECEBIDO:
      POP DX
      POPF
   }
}



void Esperar_288_ms(void)
{
	asm 
	{
		mov cx, 54612 
decrementa:
		dec cx
		cmp cx, 0
		jne decrementa
	}
}

//esta rotina levará aproximadamente 288mS para executar
void Esperar_1_Segundo(void)
{
	asm 
	{
		call Esperar_288_ms
		call Esperar_288_ms
		call Esperar_288_ms
		call Esperar_288_ms
	}
}

void Inicializa_Relogio(void)
{
	asm {
			mov HORA, 0
			mov MINUTO, 0
		}
}

void Atualiza_Relogio(void)
{
	asm {
			inc MINUTO
			cmp MINUTO, 60
			je  ZERA_MINUTO
			jmp SAI_Atualiza_Relogio
ZERA_MINUTO:
			mov MINUTO,0
			inc HORA
			cmp HORA,24
			je  ZERA_HORA
			jmp SAI_Atualiza_Relogio
ZERA_HORA:
			mov HORA,0
			jmp SAI_Atualiza_Relogio
SAI_Atualiza_Relogio:
		}
}

void Mostra_Relogio(void)
{
	asm {
			//HORA de 8 bits
			//AX de bits 16 bits
			mov al, HORA
			mov ah, 0 //pode ter sujeira
			//ax = 00000000000010001
			mov bl, 10
			div bl
			//al = quociente ah = resto			
            //al = 1 ah = 7
			mov dx, IO2  
			out dx, al 
			mov al, ah
			mov dx, IO3   
			out dx, al		
			mov al, MINUTO
			mov ah, 0
			mov bl, 10
			div bl
			//al = quociente ah = resto
            //al = 2 e ah = 3
			mov dx, IO4
			out dx, al // 2			
			mov dx, IO5
			mov al, ah
			out dx, al // 3
		}
}

void Seleciona_Linha_A(void)
{
   asm {
			mov al, 0b1110
			call MANDA_PORT_A			
	}
}

void Seleciona_Linha_B(void)
{
   asm {
			mov al, 0b1101
			call MANDA_PORT_A			
	}
}

void Seleciona_Linha_C(void)
{
   asm {
			mov al, 0b1011
			call MANDA_PORT_A			
	}
}

void Seleciona_Linha_D(void)
{
   asm {
			mov al, 0b0111
			call MANDA_PORT_A			
	}
}

void Espera_Soltar_Tecla(void)
{
      _asm
      {
	 pushf
	 push ax
	 Espera_Soltar_Tecla_Aguardando:
	    call LE_PORT_C
	    cmp al, 0b1111
	    je  Espera_Soltar_Tecla_Sai
	    jmp Espera_Soltar_Tecla_Aguardando
Espera_Soltar_Tecla_Sai:	 
	 pop ax
	 popf
      }
      
}

void RECEBER_BYTE_TECLADO_4x4_1(void)
{
	asm {
			pushf
			push cx //cl filho
			mov Contador_Teclas_Digitadas, 0
			mov bx, OFFSET NUMERO
le_caracter:
			call LER_TECLADO
			MOV al, Tecla
			cmp al, '+'
			je TRATA_CR
			cmp al, '-'
			je TRATA_CR
			cmp al, '*'
			je TRATA_CR
			cmp al, '/'
			je TRATA_CR
			cmp al, 'C'
			je TRATA_BS
			cmp Contador_Teclas_Digitadas, TAM_BYTE
			je le_caracter
			cmp al,'0'
			jl le_caracter
			cmp al,'9'
			jg le_caracter
			mov [bx], al
			inc bx
			inc Contador_Teclas_Digitadas
			call MANDAR_CARACTER
			jmp le_caracter			
TRATA_CR:
			cmp Contador_Teclas_Digitadas,2
			jne le_caracter
			mov byte ptr [bx],0  //coloca null
			MOV OPERADOR, al
			CALL MANDAR_CARACTER
			jmp SAI_RECEBER_STRING
TRATA_BS:
			cmp Contador_Teclas_Digitadas, 0
			je le_caracter
			dec bx
			mov byte ptr [bx], 0 //opcional
			dec Contador_Teclas_Digitadas
			mov al, 8 //backspace
			call MANDAR_CARACTER
			jmp le_caracter
SAI_RECEBER_STRING:
			dec bx
			dec bx
			sub byte ptr [bx],48  //2
			inc bx
			sub byte ptr [bx],48  //3
			dec bx
			mov al, [bx]
			mov cl, 10
			mul cl
			inc bx
			add ax, byte ptr [bx]
			pop cx
		     	popf
	}
}

void RECEBER_BYTE_TECLADO_4x4_2(void)
{
	asm {
			pushf
			push cx //cl filho
			mov Contador_Teclas_Digitadas, 0
			mov bx, OFFSET NUMERO
le_caracter:
			call LER_TECLADO
			MOV al, Tecla
			cmp al, '='
			je TRATA_CR
			cmp al, 'C'
			je TRATA_BS
			cmp Contador_Teclas_Digitadas, TAM_BYTE
			je le_caracter
			cmp al,'0'
			jl le_caracter
			cmp al,'9'
			jg le_caracter
			mov [bx], al
			inc bx
			inc Contador_Teclas_Digitadas
			call MANDAR_CARACTER
			jmp le_caracter			
TRATA_CR:
			cmp Contador_Teclas_Digitadas,2
			jne le_caracter
			call MANDAR_CARACTER
			mov byte ptr [bx],0  //coloca null
			jmp SAI_RECEBER_STRING
TRATA_BS:
			cmp Contador_Teclas_Digitadas, 0
			je le_caracter
			dec bx
			mov byte ptr [bx], 0 //opcional
			dec Contador_Teclas_Digitadas
			mov al, 8 //backspace
			call MANDAR_CARACTER
			jmp le_caracter
SAI_RECEBER_STRING:
			dec bx
			dec bx
			sub byte ptr [bx],48  //2
			inc bx
			sub byte ptr [bx],48  //3
			dec bx
			mov al, [bx]
			mov cl, 10
			mul cl
			inc bx
			add ax, byte ptr [bx]
			pop cx
			popf
	}
}




void Ler_Teclado(void)
{
	asm {
			call Seleciona_Linha_A
			call LE_PORT_C  //fica em AL
			cmp  al,0b0111
			je   TECLA_7
			cmp  al,0b1011
			je   TECLA_8
			cmp  al,0b1101
			je 	 TECLA_9
			cmp  al,0b1110
			je   TECLA_DIV
			call Seleciona_Linha_B
			call LE_PORT_C  //fica em AL
			cmp  al,0b0111
			je   TECLA_4
			cmp  al,0b1011
			je   TECLA_5
			cmp  al,0b1101
			je 	 TECLA_6
			cmp  al,0b1110
			je   TECLA_MUL
			call Seleciona_Linha_C
			call LE_PORT_C  //fica em AL
			cmp  al,0b0111
			je   TECLA_1
			cmp  al,0b1011
			je   TECLA_2
			cmp  al,0b1101
			je 	 TECLA_3
			cmp  al,0b1110
			je   TECLA_SUB
			call Seleciona_Linha_D
			call LE_PORT_C  //fica em AL
			cmp  al,0b0111
			je   TECLA_C
			cmp  al,0b1011
			je   TECLA_0
			cmp  al,0b1101
			je   TECLA_IGUAL
			cmp  al,0b1110
			je   TECLA_SOMA

			mov cl, 0
			jmp Sair_Ler_Teclado

TECLA_7:
			MOV CL, '7'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_8:
			MOV CL, '8'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_9:
			MOV CL, '9'
			call Espera_Soltar_Tecla
			jmp Sair_Ler_Teclado
TECLA_DIV:
			MOV CL, '/'
			call Espera_Soltar_Tecla
			jmp Sair_Ler_Teclado
TECLA_4:
			mov cl, '4'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_5:
			mov cl, '5'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_6:
			mov cl, '6'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_MUL:
			mov cl, '*'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado

TECLA_1:
			mov cl, '1'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_2:
			mov cl, '2'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_3:
			mov cl, '3'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
			
TECLA_SOMA:
			mov cl, '+'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado

TECLA_C:
			mov cl, 'C'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_0:
			mov cl, '0'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado
TECLA_IGUAL:
			mov cl, '='
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado

TECLA_SUB:
			mov cl, '-'
			call Espera_Soltar_Tecla
			JMP Sair_Ler_Teclado


Sair_Ler_Teclado:
			mov Tecla, cl
	}
}


void main(void)
 {
  INICIALIZA_8251();
  asm call INICIALIZA_8255
  set_int (0x02, (void *) & nmi_handler);




while(1){
	asm{
mov al, 7 
call MANDAR_PORT_B
call SELECIONA_DISPLAY_1

	}
}
  
 
  while(1)
	{
		asm {
Le_Novamente:
 		   call RECEBER_BYTE_TECLADO_4x4_1
 		   mov NUMERO1, al
 		   
 		   call RECEBER_BYTE_TECLADO_4x4_2
 		   mov NUMERO2, al
 		   
 		   cmp OPERADOR, '+'
 		   je SOMA
 		   cmp OPERADOR, '-'
 		   je SUBT
 		   cmp OPERADOR, '/'
 		   je DIVS
 		   cmp OPERADOR, '*'
 		   je MULT
 		   
SOMA:

	mov al, NUMERO1
	mov bl, NUMERO2
	add al, bl
	cmp al, 99
	ja MENSAGEM1
	call MANDAR_NUMERO
	call PULAR_LINHA
	jmp Le_Novamente
	
SUBT:

	mov al, NUMERO1
	mov bl, NUMERO2
	cmp bl, al
	ja MENSAGEM2
	sub al, bl
	call MANDAR_NUMERO
	call PULAR_LINHA
	jmp Le_Novamente
	
MULT:

	mov al, NUMERO1
	mov bl, NUMERO2
	mul bl
	cmp ax, 99
	ja MENSAGEM1
	call MANDAR_NUMERO
	call PULAR_LINHA
	jmp Le_Novamente
	
DIVS:

	mov al, NUMERO1
	mov bl, NUMERO2
	cmp bl, 0
	je MENSAGEM3
	div al, bl
	call MANDAR_NUMERO
	call PULAR_LINHA
	jmp Le_Novamente
	
MENSAGEM1:
    call PULAR_LINHA
	mov bx, offset Mens5  
	mov al, [bx]   
	call MANDAR_STRING
	call PULAR_LINHA
	jmp Le_Novamente
	
MENSAGEM2:
        call PULAR_LINHA
	mov bx, offset Mens6  
	mov al, [bx]   
	call MANDAR_STRING
	call PULAR_LINHA
	jmp Le_Novamente
	
MENSAGEM3:
        call PULAR_LINHA
	mov bx, offset Mens7  
	mov al, [bx]   
	call MANDAR_STRING
	call PULAR_LINHA
	jmp Le_Novamente
	
	
		}
	}


  while(1)
	{
		asm {
NUMERO:
				mov bx, offset Mens1
				call MANDAR_STRING
				call PULAR_LINHA
				call RECEBER_BYTE //al
				mov Num_Tab,al
				call PULAR_LINHA
				cmp  al, 9
				jg   ERROR
			}
				for(char i=0;i<=10;i++)
				{
					asm {
						mov al, Num_Tab
						call MANDAR_NUMERO
						mov bx, offset Mens2
						call MANDAR_STRING
						mov al, i
						call MANDAR_NUMERO
						mov bx, offset Mens3
						call MANDAR_STRING
						mov bl, Num_Tab
						mov al, i
						mul bl //al = resposta = AX
						call MANDAR_NUMERO
						call PULAR_LINHA
					}						
				}
			asm jmp fim
			asm {
ERROR:
				mov bx, offset Mens4
				call MANDAR_STRING
				call PULAR_LINHA
				jmp NUMERO
fim:
				}
	}


  while(1)
  {
	asm
	{
		call RECEBER_BYTE //retorna em AL 
		call PULAR_LINHA
		call MANDAR_NUMERO //imprimir AL
		call PULAR_LINHA
	}
  }
  //asm call INICIALIZA_8251
  Inicializa_Relogio();
 
  while(1)
  {
   asm {
		
		call Atualiza_Relogio
		call Mostra_Relogio
		call Esperar_1_Segundo
	}
  }
 }