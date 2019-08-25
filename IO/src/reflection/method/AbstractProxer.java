package reflection.method;

public interface AbstractProxer {
	public abstract void setWrapped(Object obj);
	public abstract void setOuter(SidefunctionEmplementer outer);
}
