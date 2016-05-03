.386
.model flat,stdcall

.code

Java_com_blogspot_jesfre_calculadora_InterfazNativa_sumar proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD, opb:QWORD
    finit
    fld     qword ptr [opb]
    fld     qword ptr [opa]
    fadd    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_sumar endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_restar proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD, opb:QWORD
    finit
    fld     qword ptr [opb]
    fld     qword ptr [opa]
    fsub    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_restar endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_dividir proc JNIEnv:WORD, jclass:DWORD, opa:QWORD, opb:QWORD
    finit
    fld     qword ptr [opb]
    fld     qword ptr [opa]
    fdiv    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_dividir endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_multiplicar proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD, opb:QWORD
    finit
    fld     qword ptr [opa]
    fld     qword ptr [opb]
    fmul    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_multiplicar endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_cambioSigno proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD
    finit
    fld    qword ptr [opa]
    fchs
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_cambioSigno endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_seno proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD
    finit
    fld     qword ptr [opa]
    fsin
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_seno endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_coseno proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD
    finit
    fld     qword ptr [opa]
    fcos
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_coseno endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_tangente proc JNIEnv:DWORD, jclass:DWORD, opa:QWORD
    finit
    fld qword ptr[opa]
    fcos
    fld qword ptr[opa]
    fsin
    fdiv st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_tangente endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_1y proc JNIEnv:DWORD, jclass:DWORD, opx:QWORD, opy:DWORD
    mov  ecx,opy
    fld     qword ptr [opx]
    fld     qword ptr [opx]
    dec ecx

_mulagain:
    fmul    st, st(1)
    jz _ret
    dec ecx
    jnz _mulagain

_ret:
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_1y endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_13 proc JNIEnv:DWORD, jclass:DWORD, opx:QWORD
    finit
    fld     qword ptr [opx]
    fld     qword ptr [opx]
    fmul    st, st(1)
    fld     qword ptr [opx]
    fmul    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_13 endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_12 proc JNIEnv:DWORD, jclass:DWORD, opx:QWORD
    finit
    fld     qword ptr [opx]
    fld     qword ptr [opx]
    fmul    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_x_12 endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_log proc JNIEnv:DWORD, jclass:DWORD, opa:DWORD
    fld     qword ptr [opa]
    fsqrt
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_log endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_negar proc JNIEnv:DWORD, jclass:DWORD, opa:DWORD
    mov     ecx,opa
    fld1
    fld1
    fld1
    dec ecx
_mulagain:
    jz _ret
    fadd    st(0), st(2)   ;incrementar st(3)=1 a st(0)
    fmul    st(1), st(0)
    dec ecx
    jnz _mulagain
    fld st(1)

_ret:
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_negar endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_pi proc JNIEnv:DWORD, jclass:DWORD
    fldpi
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_pi endp



Java_com_blogspot_jesfre_calculadora_InterfazNativa_uno_1x proc JNIEnv:DWORD, jclass:DWORD, opx:QWORD
    fld     qword ptr [opx]
    fld1
    fdiv    st, st(1)
    ret
Java_com_blogspot_jesfre_calculadora_InterfazNativa_uno_1x endp

END