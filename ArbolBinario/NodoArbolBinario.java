package arbolbinario;

public class NodoArbolBinario<T> {
    private T elemento;
    private NodoArbolBinario<T> izquierdo;
    private NodoArbolBinario<T> derecho;

    public NodoArbolBinario(T elemento) {
        this.elemento = elemento;
        this.izquierdo = null;
        this.derecho = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NodoArbolBinario<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbolBinario<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbolBinario<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbolBinario<T> derecho) {
        this.derecho = derecho;
    }

    public int numeroDeHijos() {
        int hijos = 0;
        if (izquierdo != null) {
            hijos += 1 + izquierdo.numeroDeHijos();
        }
        if (derecho != null) {
            hijos += 1 + derecho.numeroDeHijos();
        }
        return hijos;
    }
}