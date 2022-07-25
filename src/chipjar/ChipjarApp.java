package chipjar;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ChipjarApp {

	public static void main(String[] args) throws IOException {
		Chipjar m_chipjar = new Chipjar();

		// Used for file reading
		InputStream m_file = null;

		// Used for *actual* data reading
		DataInputStream m_data = null;

		byte[] m_rom_buffer = null;
		
		try {
			
			if (args.length == 0)
			{
				throw new Exception("No ROM Passed!");
			}
			
			m_file = new FileInputStream(args[0]);

			m_data = new DataInputStream(m_file);

			if (m_data.available() <= (0xFFF - 0x200))
			{
				m_rom_buffer = new byte[m_data.available()];
				m_data.readFully(m_rom_buffer);
			}
			else
			{
				throw new Exception("ROM Size too Large!");
			}
			
			File m_romfile = new File(args[0]);
			System.out.println("ROM Name: " + m_romfile.getName() + ", Size (Bytes): " + m_rom_buffer.length);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			if (m_file != null)
			{
				m_file.close();
			}

			if (m_data != null)
			{
				m_data.close();
			}
		}
		
		// Read the ROM to memory
		for (int i = 0; i < m_rom_buffer.length; i++)
		{
            m_chipjar.m_memory.m_ram[0x200 + i] = m_rom_buffer[i];     
        }

		m_chipjar.m_cpu.m_cpu_fde(m_chipjar);
	}

}
