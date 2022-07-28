package chipjar;

public class Chipjar {
	public Cpu m_cpu;
	public Memory m_memory;
	public Graphic m_graphics;
	
	public Chipjar() {
		this.m_cpu = new Cpu();
		this.m_memory = new Memory();
		this.m_graphics = new Graphic();
	}
}
