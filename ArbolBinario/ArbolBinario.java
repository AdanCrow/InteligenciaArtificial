package arbolbinario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArbolBinario<T> {

    protected NodoArbolBinario<T> raiz;
    protected int cuenta;

    public ArbolBinario() {
        this.raiz = null;
        this.cuenta = 0;
    }

    public ArbolBinario(T elementoRaiz, ArbolBinario<T> izquierdo, ArbolBinario<T> derecho) {
        if (elementoRaiz == null) {
            this.raiz = null;
            this.cuenta = 0;
        } else {
            this.raiz = new NodoArbolBinario<>(elementoRaiz);
            this.cuenta = 1;

            if (izquierdo != null && izquierdo.getRaiz() != null) {
                this.raiz.setIzquierdo(izquierdo.getRaiz());
                this.cuenta += izquierdo.size();
            }

            if (derecho != null && derecho.getRaiz() != null) {
                this.raiz.setDerecho(derecho.getRaiz());
                this.cuenta += derecho.size();
            }
        }
    }

    public NodoArbolBinario<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbolBinario<T> raiz) {
        this.raiz = raiz;
    }

    public int size() {
        return cuenta;
    }

    public boolean isEmpty() {
        return cuenta == 0;
    }

    public void removeLeftSubtree() {
        if (raiz != null && raiz.getIzquierdo() != null) {
            cuenta -= (1 + raiz.getIzquierdo().numeroDeHijos());
            raiz.setIzquierdo(null);
        }
    }

    public void removeRightSubtree() {
        if (raiz != null && raiz.getDerecho() != null) {
            cuenta -= (1 + raiz.getDerecho().numeroDeHijos());
            raiz.setDerecho(null);
        }
    }

    public void removeAllElements() {
        this.raiz = null;
        this.cuenta = 0;
    }

    public boolean contains(T elemento) {
        if (isEmpty() || elemento == null) {
            return false;
        }
        return buscar(raiz, elemento);
    }

    private boolean buscar(NodoArbolBinario<T> nodo, T elemento) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getElemento().equals(elemento)) {
            return true;
        }
        return buscar(nodo.getIzquierdo(), elemento) || buscar(nodo.getDerecho(), elemento);
    }

    public Iterator<T> iteratorPreOrden() {
        List<T> lista = new ArrayList<>();
        preOrden(raiz, lista);
        return lista.iterator();
    }

    private void preOrden(NodoArbolBinario<T> nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.getElemento());
            preOrden(nodo.getIzquierdo(), lista);
            preOrden(nodo.getDerecho(), lista);
        }
    }
}
