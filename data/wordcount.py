import sys
import re
from pyflink.datastream import StreamExecutionEnvironment
from pyflink.common import Types

env = StreamExecutionEnvironment.get_execution_environment()
env.set_parallelism(1)

input_path = sys.argv[1]

# Tokenizzazione corretta (rimuove punteggiatura)
def tokenize(line):
    return re.findall(r"[a-zA-Z]+", line.lower())

lines = env.read_text_file(input_path)

words = (
    lines
    .flat_map(tokenize, output_type=Types.STRING())
    .map(lambda x: (x, 1),
         output_type=Types.TUPLE([Types.STRING(), Types.INT()]))
)

counts = words.key_by(lambda x: x[0]).sum(1)

# Svuota il file prima di scrivere
open("/tmp/output_file.txt", "w").close()

def write_to_file(x):
    with open("/tmp/output_file.txt", "a") as f:
        f.write(f"{x[0]} {x[1]}\n")

counts.map(write_to_file)

env.execute("WordCount Batch")
