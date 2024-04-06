import os

if __name__ == "__main__":
    input_dir = "/path/to/protobuf/file"
    output_dir = "/path/to/output/directory"
    py_output_dir = "/path/to/output/directory"
    proto_file = "/path/to/protobuf/file/your_protobuf.proto"
    os.system(f"python -m grpc_tools.protoc "
              f"-I={input_dir} "
              f"--python_out={output_dir} "
              f"--grpc_python_out={py_output_dir} /path/to/protobuf/file/your_protobuf.proto")