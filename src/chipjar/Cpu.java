package chipjar;

import java.util.Arrays;

public class Cpu {
	private final byte REGISTER_SIZE = 16;
	private final byte STACK_SIZE = 16;
	private final short INITIAL_PROGRAM_COUNTER = 0x200;
	
	protected byte m_registers[];
	
	protected byte m_timer_delay;
	
	protected byte m_sound_delay;
	
	protected short m_index_register;
	
	protected short m_program_counter;
	
	protected short m_stack[];
	
	protected byte m_stack_pointer;
	
	public boolean m_unhandled;

	short m_opcode;
	
	protected Cpu() {
		this.m_registers = new byte[REGISTER_SIZE];
		this.m_timer_delay = 0;
		this.m_sound_delay = 0;
		this.m_index_register = 0;
		this.m_program_counter = INITIAL_PROGRAM_COUNTER;
		this.m_stack = new short[STACK_SIZE];
		this.m_stack_pointer = 0;
		this.m_opcode = 0;
		this.m_unhandled = false;
	}
	
	boolean setPixel(int x, int y, Chipjar m_chipjar) {
        if (x > 63) {
            x -= 63;
        } else if (x < 0) {
            x += 63;
        }

        if (y > 31) {
            y -= 31;
        } else if (y < 0) {
            y += 31;
        }

        int location = x + (y * 64);
        m_chipjar.m_graphics.m_video_ram[location] ^= 1;

        return m_chipjar.m_graphics.m_video_ram[location] != 1;
    }
	
	private void m_cpu_execute(short m_opcode, Chipjar m_chipjar) {
		switch (m_opcode & 0xF000) {
			case 0x0000:
					switch (m_opcode & 0x00FF) {
						case 0xE0:
							System.out.println("Clearing screen...");
							Arrays.fill(m_chipjar.m_graphics.m_video_ram, (byte) 0);	
							break;
					}
				break;
				
			case 0x1000:
				m_chipjar.m_cpu.m_program_counter = (short) ((m_opcode & 0x0FFF) - 2);
				break;
			
			case 0xA000:
				m_chipjar.m_cpu.m_index_register = (short) (m_opcode & 0x0FFF);
				break;
			
			case 0xD000:
					m_chipjar.m_cpu.m_registers[15] = 0;
			        for (int y = 0; y < (m_opcode & 0x000F); y++) {
			            int pixel = m_chipjar.m_memory.m_ram[m_chipjar.m_cpu.m_index_register + y];
			            for (int x = 0; x < 8; x++) {
			                if ((pixel & 0x80) > 0) {
			                    if (setPixel(m_chipjar.m_cpu.m_registers[((m_opcode >> 8) & 0x000F)] + x, m_chipjar.m_cpu.m_registers[((m_opcode >> 4) & 0x000F)] + y, m_chipjar)) {
			                    	m_chipjar.m_cpu.m_registers[15] = 1;
			                    }
			                }

			                pixel <<= 1;
			            }
			        }

			        m_chipjar.m_graphics.m_redraw = true;
				break;
				
			case 0x6000:
				m_chipjar.m_cpu.m_registers[(m_opcode & 0x0F00) >> 8] = (byte) (m_opcode & 0x00FF);
				break;
				
			case 0x7000:
				m_chipjar.m_cpu.m_registers[((m_opcode >> 8) & 0x000F)] += (m_opcode & 0x00FF);
				break;
				
			default:
				System.out.println("Unhandled opcode: " + (String.format("Opcode: 0x%X", m_opcode)));
				m_unhandled = true;
				break;
		}
	}
	
	private short m_cpu_fetch_decode(Chipjar m_chipjar) {
		short m_msbyte = m_chipjar.m_memory.m_memory_read(m_program_counter);
		short m_lsbyte = m_chipjar.m_memory.m_memory_read((short) (m_program_counter + 1));
		
		return (short) ((m_msbyte << 8) | m_lsbyte & 0x00FF);
	}
	
	public void m_cpu_fde(Chipjar m_chipjar) {
		short m_opcode = m_cpu_fetch_decode(m_chipjar);
		
		System.out.println("Opcode: " + String.format("0x%04X", m_opcode));
		
		m_cpu_execute(m_opcode, m_chipjar);
		
		m_program_counter += 2;
		
        if (m_chipjar.m_graphics.m_redraw) {
        	m_chipjar.m_graphics.repaint();
            m_chipjar.m_graphics.m_redraw = false;
        }
	}
}
